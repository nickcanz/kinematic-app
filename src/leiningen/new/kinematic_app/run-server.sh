LEIN1_JAR=./{{name}}-1.0.0-SNAPSHOT.jar
LEIN2_JAR=./target/{{name}}-1.0.0-SNAPSHOT.jar

if [ -e $LEIN1_JAR ]; then
  SVC_JAR=$LEIN1_JAR
fi

if [ -e $LEIN2_JAR ]; then
  SVC_JAR=$LEIN2_JAR
fi

if [ -z $SVC_JAR ]; then
  echo "Couldn't find project jar file!";
  exit 1;
fi

CLASSPATH_FILE=./.classpath

rm -f $CLASSPATH_FILE

for f in $(lein classpath | tr : \\n | grep -v lein/plugins | grep '\.jar$'); do echo -ne $f: >> $CLASSPATH_FILE; done;

exec java -server -cp $(cat $CLASSPATH_FILE):$SVC_JAR clojure.main -e "(require '{{name}}.daemon) ({{name}}.daemon/-main)"
