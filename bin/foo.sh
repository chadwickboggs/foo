#!/bin/bash

FOO_HOME="$(dirname $0)/.."

CLASSPATH="${FOO_HOME}/dist/foo.jar"

java -cp "${CLASSPATH}" com.chadwickboggs.foo.Foo $@
