#!/bin/bash

ANT=./lib/apache-ant-1.9.6/bin/ant

if [ $# -ne 1 ]; then
	echo "Falta el nombre del fichero .tgz"
	exit 1
fi

rm -rf src anterror errores_javadoc.txt 2> /dev/null
mkdir -p src 2> /dev/null
cp $1 src/practica.tgz
cd src
tar xvzf practica.tgz
cd ..
if test -d src/model ; then
 export ANT_OPTS="-Xms256m -Xmx1024m"
 #./apache-ant-1.8.2/bin/ant 2> anterror
 $ANT 2> anterror
 cat anterror | grep -v date | grep [checkstyle] > errores_javadoc.txt
# sh ./nota.sh

# echo -n "Nota pruebas documentacion: "; cat nota_doc.txt
 if test -s errores_javadoc.txt ; then
   echo "Consulta de errores en: errores_javadoc.txt"
 else
   echo "No hay errores en la documentacion"
 fi
else
 echo "Error descomprimiendo .tgz"
fi

