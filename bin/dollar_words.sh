#!/bin/bash

DW_HOME="$(dirname $0)/.."

CLASSPATH="${DW_HOME}/dist/foo.jar"

groovy -cp "${CLASSPATH}" com.chadwickboggs.foo.dollarword.DollarWordFinder $@

exit $?
