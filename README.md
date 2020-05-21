# EpicPay Java SDK

## Introduction

The EpicPay Java SDK is built as a quick-and-easy way for your Java project to
begin interacting with the EpicPay Payments API. 

The SDK was built to target Java 8, and needs support for `Lambda`s and `CompletableFuture`s.
If you need support for a version of Java without CompletableFutures such as
Android versions before SDK 24, you will need to recompile the SDK against a
compatibility library such as [android-retrofuture](https://github.com/retrostreams/android-retrofuture).

## Creating API Keys

Before starting with the SDK, you will need to create an API key online at 
[secure.epicpay.com][1]. For up-to-date documentation
on this process, please refer to [our online documentation][2].

[1]: https://secure.epicpay.com
[2]: https://developer.epicpay.com/Docs/PaymentAPI#Api_Intro

## Adding the SDK your project

### Compile and Install with Maven

If you're using Maven, you should be able to add the project to your local repository
with a simple `mvn install`. After that, you should be able to just add it to your
own project's `pom.xml` and begin using it right away:
```xml
<dependencies>
<!-- Other dependencies go here...-->
<dependency>
    <groupId>com.epicpay.epic_gateway_sdk</groupId>
    <artifactId>epic_gateway_sdk</artifactId>
    <version><!--version number in EpicPaySDK's pom.xml--></version>
</dependency>
</dependencies>
```
### Building the SDK to .jar
If you're using Maven, the above steps should be enough to get you working.
If you want to use the SDK under another build system instead, then producing a .jar 
should be as simple as running `mvn compile` in the root directory of this project.

### Instantiation
Instantiate an instance of the class EpicGateway. Its constructor needs 3 parameters:

- The API Key ID
- The API Key Password
- The API base URL, **with a trailing slash**

The API base URL will vary depending on whether you are working against the
sandbox or the live production environment.

```java
import com.epicpay.epic_gateway_sdk.EpicGateway;
// ...
    EpicGateway gateway;
    void initEpicSdk() {
        // P.S. Don't hardcode your keys in real applications!
        gateway = new EpicGateway("apikey", "apikeypasswd", "https://sandbox-api.epicpay.com/payment/v1/");
    }
// ...
```

The EpicGateway object you instantiate will be the basis for all of the SDK's
communication with the EpicPay API.

From here, the SDK will handle HTTP communication and authentication with the API.
For detailed information on all of the operations you can perform, check
https://developer.epicpay.com/Docs/PaymentAPI#Api_Intro.

## Usage

### Differences to API

As a general rule of thumb, most methods and models closely mirror those used by the EpicPay API. However, there are a few subtle differences:
- The `transactionType` field does not need to be set manually on any transactions.
- `billingAddress` and `customerAddress` fields are both represented by the same `Address` type.

### General Design Pattern
Each of the public methods exposed by your instance of the `EpicGateway` object
corresponds to one of the possible requests you could make to the EpicPay API.
Each method should have a javadoc comment with a link to its corresponding section
in the EpicPay API documentation, which you'll want to reference when crafting
your requests for the first time.

All API requests, responses, and their sub-objects are modeled by classes in the
`com.epicpay.epic_gateway_sdk.models.*` package. You may wish to use a wildcard import
for any files which use the SDK heavily.

For any API operation, the SDK provides a corresponding `Request` object that
is used to build the request. Please check the API documentation for details
on which parameters are required, as many parameters are conditionally required.

`Request` objects and their likely children all have fluent setters for your 
convenience. For instance, you may create a SaleRequest object and set its parameters
like so:
```java
new SaleRequest()
.method("credit_card")
.creditCard(new CreditCard()
    .cardNumber(cardNum)
    .cardHolderName("Joe T. Holder")
    .expMonth("01")
    .expYear("2020")
    .cvv(cardCvv))
// etc...
```
Parameters are public members, and can be set manually as well if you wish.

### Asynchronous Responses
The EpicPay SDK automatically handles threading of API requests for you, and maintains
its own `CachedThreadPool` to avoid clogging the public `ForkJoinPool`. All requests
to the SDK return `CompletableFuture`s as a result. (If you're unfamiliar with
`CompletableFuture`s, they can be used analogously to Javascript `Promise`s.) If you 
don't want asynchronous behavior, simply add a .get() to the end of the call and 
catch some checked exceptions.

```java
// Synchronous Example
PaymentResponse payRes = gateway.sale(new SaleRequest()
.creditCard(new CreditCard().cardNumber(num).cardHolderName("Name")
// etc...
.method("credit_card"))).get();
/* .get() raises some checked exceptions from CompletableFuture
that you'll need to catch.
*/
```
```java
// Asynchronous Example
CompletableFuture<PaymentResponse> payRequest = gateway.sale(new SaleRequest()
.creditCard(new CreditCard().cardNumber(num).cardHolderName("Name")
// etc...
.method("credit_card")));
payRequest.thenApply( (payResponse) => {
    // payResponse is of type PaymentResponse and is ready to use
} );
// use the method .exceptionally() to handle any exceptions that might be raised
```

For more information on how to work with `CompletableFuture`s, check the Java 8 docs:
https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html

### Error Handling

In its current version, the EpicPay SDK only throws exceptions for network-related errors,
and does not throw exceptions for errors returned by the API. Instead, you are expected
to check for errors returned by the API by checking the data of the response's returned
`Status` object. 
Check the API documentation for info on the different kinds of [Response Codes](https://developer.epicpay.com/Docs/PaymentAPI#Api_Appx_12) and [Reason Codes](https://developer.epicpay.com/Docs/PaymentAPI#Api_Appx_2) you can receive.
  
Check the `ReasonText` field for more information on any errors you encounter.
