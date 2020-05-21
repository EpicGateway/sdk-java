package com.epicpay.epic_gateway_sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.epicpay.epic_gateway_sdk.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the SDK. Not yet comprehensive,
 * currently just here to test the happy cases.
 */

// tests prefixed test_err are intended to provoke errors
// tests prefixed test_helper are sanity checks for helper methods for the tests
public class SdkTest 
{
    EpicGateway gw;
    Gson gson;
    static Random rng;
    @Before
    public void init() {
        rng = new Random();
        gson = new GsonBuilder().setPrettyPrinting().create();
        gw = new EpicGateway("0f4a4aebf4394f24a5b15b14b7cd3bae", "VMXGWwLqZmH32s17UIQTyJXLm4SvH4EE", "http://localhost:61064/");
    }
    //#region tests
    @Test
    public void testSale_CreditCard_Keyed() {
		PaymentResponse res = null;
        res = getUnchecked( gw.sale(
            new SaleRequest()
            .method("credit_card")
            .amount(9000)
            .secondaryAmount(0)
            .currency("usd")
            .clientTransactionId(uuid())
            .clientOrderId("112321")
            .clientCustomerId("123123")
            .billingAddress(makeAddr())
            .creditCard(new CreditCard()
                .cardNumber("5555555555554444")
                .cardHolderName("John Dough")
                .expMonth("02")
                .expYear("2021")
                .cvv("193")
            )
        ));
        DebugAssertions.assertApproved(res.status);
    }
    @Test
    public void testSale_CreditCard_Strip() {
        PaymentResponse res = null;
        res = getUnchecked( gw.sale(
            new SaleRequest()
            .method("card_track")
            .amount(9000)
            .secondaryAmount(0)
            .currency("usd")
            .clientTransactionId(uuid())
            .clientOrderId("112321")
            .clientCustomerId("123123")
            .billingAddress(makeAddr())
            .creditCard(makeCCStrip())
        ));
        DebugAssertions.assertApproved(res.status);
    }
    public void testSale_eCheck() {
        PaymentResponse res = null;
        res = getUnchecked( gw.sale(
            new SaleRequest()
            .method("echeck")
            .amount(9000)
            .secondaryAmount(0)
            .currency("usd")
            .clientTransactionId(uuid())
            .clientOrderId("112321")
            .clientCustomerId("123123")
            .billingAddress(makeAddr())
            .bankAccount(makeAcct())
            .secCode("1234")
        ));
        DebugAssertions.assertApproved(res.status);
    }
    @Test
    public void test_Auth() {
		PaymentResponse res = getUnchecked(gw.authorize( 
        new AuthorizationRequest()
            .amount(111)
            .currency("usd")
            .method("credit_card")
            .creditCard(new CreditCard()
                .cardNumber("3530111333300000")
                .cardHolderName("Joe Jones")
                .expMonth("02")
                .expYear("23")
                .cvv("123")
            )
            .billingAddress(makeAddr())
		));
        DebugAssertions.assertApproved(res.status);
	}
    @Test
    public void test_AuthToVoid() {
		PaymentResponse res = getUnchecked(gw.authorize( new AuthorizationRequest()
            .amount(111)
            .currency("usd")
            .method("credit_card")
            .creditCard(new CreditCard()
                .cardNumber("3530111333300000")
                .cardHolderName("Joe Jones")
                .expMonth("02")
                .expYear("23")
                .cvv("123")
            )
            .billingAddress(makeAddr())
        ));
        DebugAssertions.assertApproved(res.status);
        PaymentResponse res2 = getUnchecked(gw.voidTransaction(new VoidRequest()
            .amount(res.result.payment.amount)
            .transactionId(res.result.payment.transactionId)
            .clientTransactionId(uuid())));
        DebugAssertions.assertReceived(res2.status);
    
	}
	@Test
	public void test_AuthToCapture() {
        PaymentResponse auth = getUnchecked(gw.authorize( new AuthorizationRequest()
            .amount(111)
            .currency("usd")
            .method("credit_card")
            .creditCard(new CreditCard()
                .cardNumber("3530111333300000")
                .cardHolderName("Joe Jones")
                .expMonth("02")
                .expYear("23")
                .cvv("123")
            )
            .billingAddress(makeAddr())
        ));
        DebugAssertions.assertApproved(auth.status);
        CaptureRequest cr = new CaptureRequest()
        .amount(auth.result.payment.amount)
        .currency(auth.result.payment.currency)
        .transactionId(auth.result.payment.transactionId)
        .clientTransactionId(uuid());
			getUnchecked(gw.capture(cr));
    }
    @Test
    public void test_CardSaleToRefund() {
        PaymentResponse res = getUnchecked( gw.sale(
            new SaleRequest()
            .method("credit_card")
            .amount(9000)
            .secondaryAmount(0)
            .currency("usd")
            .clientTransactionId(uuid())
            .clientOrderId("112321")
            .clientCustomerId("123123")
            .billingAddress(makeAddr())
            .creditCard(new CreditCard()
                .cardNumber("5555555555554444")
                .cardHolderName("John Dough")
                .expMonth("02")
                .expYear("2021")
                .cvv("193")
            )
        ));
        DebugAssertions.assertApproved(res.status);
        PaymentResponse res2 = getUnchecked( gw.refund(new RefundRequest()
            .amount(res.result.payment.amount)
            .clientTransactionId(uuid())
            .transactionId(res.result.payment.transactionId)
        ));
        DebugAssertions.assertReceived(res2.status);
    }
    @Test
    public void test_err_CaptureAfterVoid() {
        PaymentResponse auth = getUnchecked(gw.authorize( new AuthorizationRequest()
            .amount(111)
            .currency("usd")
            .method("credit_card")
            .creditCard(new CreditCard()
                .cardNumber("3530111333300000")
                .cardHolderName("Joe Jones")
                .expMonth("02")
                .expYear("23")
                .cvv("123")
            )
            .billingAddress(makeAddr())
        ));
        DebugAssertions.assertApproved(auth.status);
        PaymentResponse res2 = getUnchecked(gw.voidTransaction( new VoidRequest()
            .amount(auth.result.payment.amount)
            .clientTransactionId(uuid())
            .transactionId(auth.result.payment.transactionId)
        ));
        DebugAssertions.assertReceived(res2.status);
        PaymentResponse res3 = getUnchecked(gw.capture( new CaptureRequest()
            .amount(auth.result.payment.amount)
            .clientTransactionId(uuid())
            .transactionId(auth.result.payment.transactionId)
        ));
        DebugAssertions.assertError(res3.status);
    }
    @Test
    public void test_MultiUseToken() {
        MultiUseTokenRequest ccTokenReq = new MultiUseTokenRequest()
        .method("credit_card")
        .creditCard(makeCC());
        MultiUseTokenResponse res1 = getUnchecked(gw.registerToken(ccTokenReq));
        DebugAssertions.assertReceived(res1.status);
        SaleRequest req = new SaleRequest()
        .amount(1300)
        .billingAddress(makeAddr())
        .clientCustomerId(uuid())
        .clientTransactionId(uuid())
        .currency("usd")
        .method("card_token")
        .token(makeCCTok(res1.result.token.tokenNumber));
        PaymentResponse res2 = getUnchecked(gw.sale(req));
        DebugAssertions.assertApproved(res2.status);
        
    }
    @Test
    public void test_OneTimeToken() {
        // If this test fails, check and see if you need to change what localIp() returns
        // to match your machine/configuration.
        
        JwtTokenResponse res1 = getUnchecked(gw.requestJwt(new JwtTokenRequest().ip(localIp())));
        DebugAssertions.assertReceived(res1.status);
        TokenResponse res2 = getUnchecked(gw.registerOneTimeToken(res1.result.jwt, new OneTimeTokenRequest()
        .method("credit_card").accountNumber(SampleCardNumbers.Visa)));
        DebugAssertions.assertReceived(res2.status);
        PaymentResponse res3 = getUnchecked(gw.sale(new SaleRequest()
        .method("card_token")
        .amount(9199)
        .billingAddress(makeAddr())
        .currency("usd")
        .token(makeCCTok(res2.result.token.tokenNumber))));
        DebugAssertions.assertApproved(res3.status);
    }
    @Test
    public void test_err_WalletNoCustomer() {
        WalletResponse res1 = getUnchecked(gw.addWalletItem(new AddWalletRequest()
        .method("credit_card")
        .creditCard(makeCC())));
        DebugAssertions.assertError(res1.status);
    }
    @Test
    public void test_AddWalletCC() {
        WalletResponse res1 = getUnchecked(gw.addWalletItem(new AddWalletRequest()
        .method("credit_card")
        .creditCard(makeCC())
        .customerAddress(makeAddr())
        ));
        DebugAssertions.assertReceived(res1.status);
    }
    public void test_AddWalletEcheck() {

    }
    @Test
    public void test_AddWalletCC_ToSale() {
        WalletResponse res1 = getUnchecked(gw.addWalletItem(new AddWalletRequest()
        .method("credit_card")
        .creditCard(makeCC())
        .customerAddress(makeAddr())
        ));
        DebugAssertions.assertReceived(res1.status);
        PaymentResponse res2 = getUnchecked(gw.sale(new SaleRequest()
        .amount(9001)
        .currency("usd")
        .method("wallet")
        .billingAddress(makeAddr())
        .clientCustomerId(uuid())
        .clientTransactionId(uuid())
        .wallet(new Wallet().cvv("123").walletId(res1.result.wallet.walletId))
        ));
        DebugAssertions.assertApproved(res2.status);
    }
    public void test_AddWalletEcheck_ToSale() {

    }
    @Test
    public void test_AddWallet_TwoCCs_TwoSales() {
        WalletResponse res1 = getUnchecked(gw.addWalletItem(new AddWalletRequest()
        .method("credit_card")
        .creditCard(makeCC(1))
        .customerAddress(makeAddr())
        ));
        DebugAssertions.assertReceived(res1.status);
        WalletResponse res2 = getUnchecked(gw.addWalletItem(new AddWalletRequest()
        .method("credit_card")
        .creditCard(makeCC(0))
        .customerId(res1.result.wallet.customerId)
        ));
        DebugAssertions.assertReceived(res2.status);
        PaymentResponse res3 = getUnchecked(gw.sale(new SaleRequest()
        .amount(9001)
        .currency("usd")
        .method("wallet")
        .billingAddress(makeAddr())
        .clientCustomerId(uuid())
        .clientTransactionId(uuid())
        .wallet(new Wallet().cvv("123").walletId(res1.result.wallet.walletId))
        ));
        DebugAssertions.assertApproved(res3.status);
        PaymentResponse res4 = getUnchecked(gw.sale(new SaleRequest()
        .amount(9001)
        .currency("usd")
        .method("wallet")
        .billingAddress(makeAddr())
        .clientCustomerId(uuid())
        .clientTransactionId(uuid())
        .wallet(new Wallet().cvv("123").walletId(res2.result.wallet.walletId))
        ));
        DebugAssertions.assertApproved(res4.status);
        assertNotEquals("Two separate wallets should have been created!", 
            res1.result.wallet.walletId, res2.result.wallet.walletId);
    }
    @Test
    public void test_EditWalletCC() {
        WalletResponse res1 = getUnchecked(gw.addWalletItem(new AddWalletRequest()
        .method("credit_card")
        .creditCard(makeCC())
        .customerAddress(makeAddr())
        .billingAddress(makeAddr(0))
        ));
        DebugAssertions.assertReceived(res1.status);
        WalletResponse res2 = getUnchecked(gw.editWalletItem(new EditWalletRequest()
        .accountHolderName("NewAcctHolder Dude")
        .expMonth("11")
        .expYear("2023")
        .billingAddress(makeAddr(9283))
        .walletId(res1.result.wallet.walletId)));
        DebugAssertions.assertReceived(res2.status);
    }
    @Test
    public void test_DeleteWalletCC() {
        WalletResponse res1 = getUnchecked(gw.addWalletItem(new AddWalletRequest()
        .method("credit_card")
        .creditCard(makeCC())
        .customerAddress(makeAddr())
        .billingAddress(makeAddr(0))
        ));
        DebugAssertions.assertReceived(res1.status);
        WalletResponse res2 = getUnchecked(gw.deleteWalletItem(new DeleteWalletRequest()
        .walletId(res1.result.wallet.walletId)));
        DebugAssertions.assertReceived(res2.status);
    }
    @Test
    public void test_EditCustomer() {
        WalletResponse res1 = getUnchecked(gw.addWalletItem(new AddWalletRequest()
        .method("credit_card")
        .creditCard(makeCC())
        .customerAddress(makeAddr())
        ));
        DebugAssertions.assertReceived(res1.status);
        CustomerResponse res2 = getUnchecked(gw.editCustomer(new EditCustomerRequest()
        .customerId(res1.result.wallet.customerId)
        .clientCustomerId(uuid())
        .customerAddress(makeAddr())));
        DebugAssertions.assertReceived(res2.status);
        assertEquals(res1.result.wallet.customerId, res2.result.customer.customerId);

    }
    // Test idea: Use wallet after delete
    @Test
    public void test_GetFromClientId() {
        String clientId = uuid();
        PaymentResponse res1 = getUnchecked( gw.sale(
            new SaleRequest()
            .method("credit_card")
            .amount(9000)
            .secondaryAmount(0)
            .currency("usd")
            .clientTransactionId(clientId)
            .clientOrderId("112321")
            .clientCustomerId("123123")
            .billingAddress(makeAddr())
            .creditCard(makeCC())
        ));
        DebugAssertions.assertApproved(res1.status);
        PaymentResponse res2 = getUnchecked( gw.getTransaction (new GetTransactionRequest()
        .id(clientId)
        .idSource("client")));
        DebugAssertions.assertApproved(res2.status);
        assertEquals(res2.result.payment.amount, res1.result.payment.amount);
        assertEquals(res2.result.payment.currency.toLowerCase(), res1.result.payment.currency.toLowerCase());
        assertEquals(res2.result.payment.creditCard.cardHolderName, res1.result.payment.creditCard.cardHolderName);
    }
    @Test
    public void test_GetFromEpicId() {
        String clientId = uuid();
        PaymentResponse res1 = getUnchecked( gw.sale(
            new SaleRequest()
            .method("credit_card")
            .amount(9000)
            .secondaryAmount(0)
            .currency("usd")
            .clientTransactionId(clientId)
            .clientOrderId("112321")
            .clientCustomerId("123123")
            .billingAddress(makeAddr())
            .creditCard(makeCC())
        ));
        DebugAssertions.assertApproved(res1.status);
        PaymentResponse res2 = getUnchecked( gw.getTransaction (new GetTransactionRequest()
        .id(res1.result.payment.transactionId)
        .idSource("epicpay")));
        DebugAssertions.assertApproved(res2.status);
        assertEquals(res2.result.payment.amount, res1.result.payment.amount);
        assertEquals(res2.result.payment.currency.toLowerCase(), res1.result.payment.currency.toLowerCase());
        assertEquals(res2.result.payment.creditCard.cardHolderName, res1.result.payment.creditCard.cardHolderName);
    }
    @Test
    public void test_AddSubscription() {
        SubscriptionResponse res1 = getUnchecked(gw.addSubscription(new AddSubscriptionRequest()
        .clientOrderID(uuid())
        .clientCustomerID(uuid())
        .currency("usd")
        .amount(1)
        .frequency("every_n_weeks")
        .period(2)
        .nextPaymentDate("2023-01-02")
        .lastPaymentDate("2024-01-02")
        .alert(true)
        .method("credit_card")
        .creditCard(makeCC())
        .customerAddress(makeAddr())
        .billingAddress(makeAddr())
        ));
        DebugAssertions.assertReceived(res1.status);
    }
    public void test_AddSubscriptionWallet() {

    }
    @Test
    public void test_EditSubscription() {
        String cOrderId = uuid();
        String cCustId = uuid();
        SubscriptionResponse res1 = getUnchecked(gw.addSubscription(new AddSubscriptionRequest()
        .clientOrderID(cOrderId)
        .clientCustomerID(cCustId)
        .currency("usd")
        .amount(1)
        .frequency("every_n_weeks")
        .period(2)
        .nextPaymentDate("2023-01-02")
        .lastPaymentDate("2024-01-02")
        .alert(true)
        .method("credit_card")
        .creditCard(makeCC())
        .customerAddress(makeAddr(1))
        .billingAddress(makeAddr(2))
        ));
        DebugAssertions.assertReceived(res1.status);
        EditSubscriptionRequest req2 = new EditSubscriptionRequest()
        .subscriptionId(res1.result.subscription.subscriptionId)
        .clientOrderID(cOrderId)
        .clientCustomerID(cCustId)
        .currency("usd")
        .amount(100)
        .frequency("every_n_weeks")
        .period(2)
        .nextPaymentDate("2023-01-02")
        .lastPaymentDate("2024-01-02")
        .alert(true)
        .method("credit_card")
        .creditCard(makeCC())
        .customerAddress(makeAddr(3))
        .billingAddress(makeAddr(4));
        SubscriptionResponse res2 = getUnchecked(gw.editSubscription(req2));
        DebugAssertions.assertReceived(res2.status);
        assertNotEquals(res1.result.subscription.amount, res2.result.subscription.amount);
    }
    @Test
    public void test_EditSubscriptionToEcheck() {
        String cOrderId = uuid();
        String cCustId = uuid();
        SubscriptionResponse res1 = getUnchecked(gw.addSubscription(new AddSubscriptionRequest()
        .clientOrderID(cOrderId)
        .clientCustomerID(cCustId)
        .currency("usd")
        .amount(1)
        .frequency("every_n_weeks")
        .period(2)
        .nextPaymentDate("2023-01-02")
        .lastPaymentDate("2024-01-02")
        .alert(true)
        .method("credit_card")
        .creditCard(makeCC())
        .customerAddress(makeAddr(1))
        .billingAddress(makeAddr(2))
        ));
        DebugAssertions.assertReceived(res1.status);
        EditSubscriptionRequest req2 = new EditSubscriptionRequest()
        .subscriptionId(res1.result.subscription.subscriptionId)
        .clientOrderID(cOrderId)
        .clientCustomerID(cCustId)
        .currency("usd")
        .amount(100)
        .frequency("every_n_weeks")
        .period(2)
        .nextPaymentDate("2023-01-02")
        .lastPaymentDate("2024-01-02")
        .alert(true)
        .method("echeck")
        .bankAccount(makeAcct())
        .customerAddress(makeAddr(3))
        .billingAddress(makeAddr(4));
        SubscriptionResponse res2 = getUnchecked(gw.editSubscription(req2));
        DebugAssertions.assertReceived(res2.status);
        assertNotEquals(res1.result.subscription.amount, res2.result.subscription.amount);
    }
    @Test
    public void test_DeleteSubscription() {
        SubscriptionResponse res1 = getUnchecked(gw.addSubscription(new AddSubscriptionRequest()
        .clientOrderID(uuid())
        .clientCustomerID(uuid())
        .currency("usd")
        .amount(1)
        .frequency("every_n_weeks")
        .period(2)
        .nextPaymentDate("2023-01-02")
        .lastPaymentDate("2024-01-02")
        .alert(true)
        .method("credit_card")
        .creditCard(makeCC())
        .customerAddress(makeAddr(1))
        .billingAddress(makeAddr(2))
        ));
        DebugAssertions.assertReceived(res1.status);
        SubscriptionResponse  res2 = 
        getUnchecked(gw.deleteSubscription(new DeleteSubscriptionRequest()
        .subscriptionId(res1.result.subscription.subscriptionId)));
        DebugAssertions.assertReceived(res2.status);
    }
    @Test
    public void test_SuspendUnsuspendSubscription() {
        SubscriptionResponse res1 = getUnchecked(gw.addSubscription(new AddSubscriptionRequest()
        .clientOrderID(uuid())
        .clientCustomerID(uuid())
        .currency("usd")
        .amount(1)
        .frequency("every_n_weeks")
        .period(2)
        .nextPaymentDate("2023-01-02")
        .lastPaymentDate("2024-01-02")
        .alert(true)
        .method("credit_card")
        .creditCard(makeCC())
        .customerAddress(makeAddr(1))
        .billingAddress(makeAddr(2))
        ));
        DebugAssertions.assertReceived(res1.status);
        SuspendSubscriptionResponse res2 = 
        getUnchecked(gw.suspendSubscription(new SuspendSubscriptionRequest()
        .subscriptionId(res1.result.subscription.subscriptionId)));
        DebugAssertions.assertReceived(res2.status);
        assertEquals("Suspended", res2.result.subscription.status);
        SuspendSubscriptionResponse res3 = 
        getUnchecked(gw.unsuspendSubscription(new UnsuspendSubscriptionRequest()
        .subscriptionId(res1.result.subscription.subscriptionId)));
        DebugAssertions.assertReceived(res3.status);
        assertEquals("Unsuspended", res3.result.subscription.status);
    }
    //#endregion
    //#region helper tests
    // Ensure that providing makeAddr with a seed generates a consistent address every time.
    @Test
    public void test_helper_addrSeed() {
        Address addr1a = makeAddr(0);
        Address addr1b = makeAddr(0);
        Address addr2a = makeAddr(100);
        Address addr2b = makeAddr(100);
        assertEquals(addr1a.addressLine1, addr1b.addressLine1);
        assertEquals(addr2a.addressLine1, addr2b.addressLine1);
        assertNotEquals(addr1a.addressLine1, addr2a.addressLine1);

    }
    //#endregion
    //#region helpers

    static <T> T getUnchecked(CompletableFuture<T> future) {
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	static String uuid() {
		return UUID.randomUUID().toString();
    }
    //#region mock data creators
    static String localIp() {
        //Assumes API is running on Localhost over ipv6!!!
        // In general, this should be the IP that the API sees your machine as,
        // in string form. A handful of tests will fail if this is wrong.
        return "::1";
    }
    static CreditCard makeCC() {
        return makeCC(0);
    }
    // no, not like that. get your mind out of the gutter.
    static CreditCard makeCCStrip() {
        return new CreditCard()
        .cardHolderName(makeFirstName() + " " + makeLastName())
        .cardTrack(SampleCardNumbers.StripDiscover);
    }
    // intended for when 2+ distinct credit cards are needed
    static <T> T randFromArray(T[] arr) {
        return arr[rng.nextInt(arr.length)];
    }
    static BankAccount makeAcct() {
        String[] routingNumbers = {
            "011103093",
            "067014822",
            "211274450",
            "211370545",
            "054001725",
            "011400071",
            "031201360",
            "026013673",
            "021302567",
            "053902197",
            "036001808",
            "011600033"
        };
        String[] accountTypes = {
            "personal_checking",
            "personal_savings",
            "business_checking",
            "business_savings"
        };
        return new BankAccount()
        .accountType(randFromArray(accountTypes))
        .routingNumber(randFromArray(routingNumbers))
        .accountNumber("123412341234")
        .accountHolderName(makeFirstName() + " " + makeLastName());
    }
    static BankAccount makeAcct(int seed) {
        rng.setSeed(seed);
        return makeAcct();        
    }
    static CreditCard makeCC(int index) {
        String[] cards = {
            "5555555555554444",
            "6011000990139424",
            "378282246310005",
            "4111111111111111",
            "30569309025904",
            "38520000023237",
            "3530111333300000"
        };
        index = (index % cards.length);
        return new CreditCard()
        .cardHolderName("Card H. Older")
        .cardNumber(cards[index])
        .expMonth("09")
        .expYear("2031")
        .cvv("182");
    }
    static CardToken makeCCTok(String tokenNumber) {
        return new CardToken()
        .accountHolderName(makeFirstName() + " " + makeLastName())
        .tokenType("epicpay")
        .expMonth("02")
        .expYear("2031")
        .cvv("191")
        .tokenNumber(tokenNumber);
    }
    // take an RNG seed to guarantee different addresses are generated in test cases
    // so that tests won't have a random chance of failing due to RNG collision
    static Address makeAddr(int seed) {
        rng.setSeed(seed);
        return makeAddr();
    }
    static Address makeAddr() {
        return new Address()
        .addressLine1(makeAddrLine1())
        .addressLine2(makeAddrLine2())
        .city(makeCity())
        .companyName(makeCompanyName())
        .countryCode(makeCountryCode())
        .email(makeEmail())
        .firstName(makeFirstName())
        .lastName(makeLastName())
        .phone(makePhone())
        .postalCode(makePostalCode())
        .stateProvince(makeState());
    }

    static String makeAddrLine1() {
        String[] names = {"Preston", "Parker", "Wheelbarrow", "Millhouse", "Chesterfield"};
        String[] types = {"Road", "Blvd", "Pkwy", "Dr", "Way"};
        return rng.nextInt(9999) + " " + randFromArray(names) + " " + randFromArray(types); 
    }
    static String makeAddrLine2() {
        if (rng.nextDouble() < 0.5) {
            return "";
        }
        String[] types = {"Suite", "Apt.", "Bldg."};
        return randFromArray(types) + " " + rng.nextInt(9999);
    }
    static String makeCity() {
        String[] prefixes = {"Harris", "Maple", "Pitts", "Coffee", "Oak"};
        String[] suffixes = {"ville", "burg", "town", " Depot", " Crossing", "ing", "wick", "thorpe"};
        return randFromArray(prefixes) + randFromArray(suffixes);
    }
    static String makeCompanyName() {
        return "Cocoa Co.";
    }
    static String makeCountryCode() {
        return "US";
    }
    static String makeEmail() {
        return "email@example.com";
    }
    static String makeFirstName() {
        String[] names = {"James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael", "Linda"};
        return randFromArray(names);
    }
    static String makeLastName() {
        String[] names = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson"};
        return randFromArray(names);
    }
    static Phone makePhone() {
        String acc = "";
        acc += rng.nextInt(8) + 2; //no area codes starting with 0 or 1
        for (int i = 0; i < 10; i++) {
            acc += rng.nextInt(10);
        }
        return new Phone().number(acc).type("mobile");
    }
    static String makePostalCode() {
        return rng.nextInt(89999) + 10000 + "-1234"; // lazy way to guarantee 5 digits :^)
    }
    static String makeState() {
        String[] states = {"Alaska", "North Dakota", "Texas", "New York", "California"};
        return randFromArray(states);
    }
    //#endregion
    //#endregion
}
class SampleCardNumbers {
    public static final String Mastercard = "5555555555554444";
    public static final String Discover = "6011000990139424";
    public static final String Amex = "378282246310005";
    public static final String Visa = "4111111111111111";
    public static final String Diners = "30569309025904";
    public static final String Diners2 = "38520000023237";
    public static final String JCB = "3530111333300000";
    public static final String StripDiscover = "%B6011000992669337^NETWORK/DISCOVER          ^19102011000152309102?;6011000992669337=19102011000152309102?";
}