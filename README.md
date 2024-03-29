# Duct server.http.immutant

[![Build Status](https://travis-ci.org/agrison/duct-pohjavirta.svg?branch=master)](https://travis-ci.org/agrison/duct-pohjavirta)

Integrant multimethods for running an [Pohjavirta][] (Undertow) HTTP server for the
[Duct][] framework.

[pohjavirta]: https://github.com/metosin/pohjavirta
[duct]: https://github.com/duct-framework/duct

## Installation

To install, add the following to your project `:dependencies`:

    [me.grison/duct-pohjavirta "0.1.0"]

## Usage

This library adds Integrant methods that dispatch off the
`:duct.server.http/pohjavirta` key, which is derived from
`:duct.server/http`. The corresponding value is a map of options for
the Immutant Ring adapter, plus a `:handler` key that takes a handler
function.

For example:

```clojure
{:duct.server.http/pohjavirta
 {:port    3000
  :handler (fn [request]
             {:status  200
              :headers {"Content-Type" "text/plain"}
              :body    "Hello World"})}}
```

A `:logger` key may also be specified, which will be used to log when
the server starts and when it stops. The value of the key should be an
implementation of the `duct.logger/Logger` protocol from the
[duct.logger][] library

[duct.logger]: https://github.com/duct-framework/logger

## License

Copyright © 2019 Alexandre Grison

Adapted from work done by James Reeves in both 
[server.http.jetty](http://github.com/duct-framework/server.http.jetty)
and [server.http.http-kit](http://github.com/duct-framework/server.http.http-kit)

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
