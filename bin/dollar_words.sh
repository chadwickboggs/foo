#!/usr/bin/env bash

DW_HOME="$(dirname $0)/.."
CLASSPATH="${DW_HOME}/dist/groovy.jar"

USAGE="$(basename $0) <options> \n
\n
\t	Run Dollar Words. \n
\n
\t	Options \n
\n
\t\t		[-h|--help]\t\t\t\t			This message gets printed only. \n
\t\t		[-a|--arg] <the argument>\t	The CLI argument.  Default: \"\" \n
"

args=$(getopt -o "hA:" -l "help,ARG" -- "$@")
eval set -- "$args"
while true; do
  case "$1" in
		-h | --help) 	echo -e ${USAGE}; exit;;
		-a | --arg) shift; arg=".$1"; shift;;
		--) shift; break;;
		*) echo "Internal error!"; exit 1;;
  esac
done

groovy -cp "${CLASSPATH}" com.chadwickboggs.foo.dollarword.Main $@

exit $?
