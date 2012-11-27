SVC_JAR=./{{name}}-1.0.0-SNAPSHOT.jar
CLASSPATH_FILE=./.classpath

rm -f $CLASSPATH_FILE

for f in $(lein classpath | tr : \\n | grep -v lein/plugins | grep '\.jar$'); do echo -ne $f: >> $CLASSPATH_FILE; done;

exec java -server -cp $(cat $CLASSPATH_FILE):$SVC_JAR clojure.main -e "(require '{{name}}.daemon) ({{name}}.daemon/-main)"
