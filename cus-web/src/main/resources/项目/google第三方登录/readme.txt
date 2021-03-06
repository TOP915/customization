https://console.developers.google.com/apis/credentials?project=serious-hall-230108 谷歌开发者

1、google账号
mspudding@163.com dyn192421.
2、项目名称 *
   cus-web
3、项目 ID *
   serious-hall-230108
4、客户端ID：662426624378-p313vqiou5mhj80coqfkdacalmg5ddfk.apps.googleusercontent.com
   秘钥：5K6RUxU8chPObdc2vjPJQleW
5、对接原理：
使用OAuth 2.0访问Google API
Google API使用OAuth 2.0协议进行身份验证和授权。Google支持常见的OAuth 2.0方案，例如Web服务器，已安装和客户端应用程序。

首先，从Google API控制台获取OAuth 2.0客户端凭据。然后，您的客户端应用程序从Google授权服务器请求访问令牌，从响应中提取令牌，并将令牌发送到您要访问的Google API。有关将OAuth 2.0与Google结合使用的交互式演示（包括使用您自己的客户端凭据的选项），请试用OAuth 2.0 Playground。

此页面概述了Google支持的OAuth 2.0授权方案，并提供了更详细内容的链接。有关使用OAuth 2.0进行身份验证的详细信息，请参阅OpenID Connect。

注意： 鉴于实现正确性的安全性，我们强烈建议您在与Google的OAuth 2.0端点交互时使用OAuth 2.0库。最佳做法是使用其他人提供的经过良好调试的代码，它将帮助您保护自己和用户。有关更多信息，请参阅客户端库。
基本步骤
使用OAuth 2.0访问Google API时，所有应用程序都遵循基本模式。在较高的层次上，您遵循以下四个步骤：

1.从Google API控制台获取OAuth 2.0凭据。
访问 Google API控制台 以获取OAuth 2.0凭据，例如Google和您的应用程序都知道的客户端ID和客户端密码。这组值根据您正在构建的应用程序类型而有所不同。例如，JavaScript应用程序不需要保密，但Web服务器应用程序不需要保密。

2.从Google Authorization Server获取访问令牌。
在您的应用程序可以使用Google API访问私人数据之前，它必须获取授予该API访问权限的访问令牌。单个访问令牌可以授予对多个API的不同程度的访问权限。调用的变量参数scope控制访问令牌允许的资源和操作集。在访问令牌请求期间，您的应用程序会在scope参数中发送一个或多个值。

有多种方法可以提出此请求，它们会根据您正在构建的应用程序类型而有所不同。例如，JavaScript应用程序可能使用浏览器重定向到Google请求访问令牌，而安装在没有浏览器的设备上的应用程序使用Web服务请求。

某些请求需要身份验证步骤，用户使用其Google帐户登录。登录后，系统会询问用户是否愿意授予应用程序请求的权限。此过程称为用户同意。

如果用户授予权限，Google Authorization Server会向您的应用程序发送访问令牌（或您的应用程序可用于获取访问令牌的授权码）。如果用户未授予权限，则服务器返回错误。

通常，最佳做法是在需要访问时逐步请求范围，而不是预先请求范围。例如，想要支持购买的应用不应在用户按下“购买”按钮之前请求Google电子钱包访问权限; 请参阅增量授权。

3.将访问令牌发送到API。
应用程序获取访问令牌后，会将令牌发送到HTTP授权标头中的Google API。可以将标记作为URI查询字符串参数发送，但我们不建议这样做，因为URI参数最终可能会出现在不完全安全的日志文件中。此外，避免创建不必要的URI参数名称是一种很好的REST实践。

访问令牌仅对scope令牌请求中描述的操作和资源集有效。例如，如果为Google+ API发放了访问令牌，则它不会授予对Google通讯录API的访问权限。但是，您可以多次将该访问令牌发送到Google+ API以执行类似操作。

4.如有必要，刷新访问令牌。
访问令牌的生命周期有限。如果您的应用需要在单个访问令牌的生命周期之后访问Google API，则可以获取刷新令牌。刷新令牌允许您的应用程序获取新的访问令牌。

注意： 在安全的长期存储中保存刷新令牌，并且只要它们仍然有效，就继续使用它们。限制适用于每个客户端 - 用户组合以及所有客户端中的每个用户发布的刷新令牌的数量，并且这些限制是不同的。如果您的应用程序请求足够的刷新令牌超过其中一个限制，则较旧的刷新令牌将停止工作。
方案
Web服务器应用程序
Google OAuth 2.0端点支持使用PHP，Java，Python，Ruby和ASP.NET等语言和框架的Web服务器应用程序。

当您的应用程序将浏览器重定向到Google URL时，授权序列就会开始; URL包括指示所请求的访问类型的查询参数。Google处理用户身份验证，会话选择和用户同意。结果是授权代码，应用程序可以交换访问令牌和刷新令牌。

应用程序应存储刷新令牌以供将来使用，并使用访问令牌访问Google API。访问令牌到期后，应用程序将使用刷新令牌获取新令牌。

您的应用程序向Google授权服务器发送令牌请求，接收授权代码，交换令牌代码，并使用令牌调用Google API端点。

附件：图webflow.png