Servlet Basics
=================================================

These demos illustrate basic concepts related to Java servlets using embedded Jetty.

## Jetty Setup ##

To add Jetty as a user library in Eclipse, you will need the aggregate `jar` files located at the following links:

- [`jetty-all-9.x-uber.jar`](https://repo1.maven.org/maven2/org/eclipse/jetty/aggregate/jetty-all/)

Look for the latest `9.x` release. The directory is sorted lexicographically not numerically (e.g. by text not number), so version numbers like `9.4.22` will appear before version numbers like `4.4.3` even though it is a later version. 

:bulb: _You no longer need to download the servlet `jar` files separately---they now come bundled in the `jetty-all` jar file._

You may also want to consider the Apache Commons Text library, which has several useful utilities. You need the Apache Commons Lang library as well:

- [`commons-lang3-3.x.x.jar`](http://commons.apache.org/proper/commons-lang/)
- [`commons-text-1.x.x.jar`](http://commons.apache.org/proper/commons-text/)

See the [Adding User Party Libraries in Eclipse](https://usf-cs212-fall2019.github.io/guides/eclipse/adding-user-libraries-in-eclipse.html) guide for details.

## Relevant Resources ##

The following API documentation may be useful:

- [Jetty v9.x (Stable)](http://www.eclipse.org/jetty/documentation/current/)

The following pre-recorded videos may be useful:

- [Dynamic Webpages - Setup](https://youtu.be/X5gr591JsLQ)
- [Dynamic Webpages - Context Server](https://youtu.be/kJDmEom17-Q)

The following other websites might be useful:

- [Jetty Reference](http://www.eclipse.org/jetty/documentation/current/)
- [Jetty Development Guide - Chapter 21. Embedding](https://www.eclipse.org/jetty/documentation/current/advanced-embedding.html)

## CSS Frameworks ##

[Bulma](https://bulma.io/) &bull;
[Bootstrap](https://getbootstrap.com/) &bull;
[Pure](https://purecss.io/) &bull;
[Materialize](https://materializecss.com/) &bull;
[Foundation](https://foundation.zurb.com/) &bull;
[Semantic UI](https://semantic-ui.com/) &bull;
*and many more*