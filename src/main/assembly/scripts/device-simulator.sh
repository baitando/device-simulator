BASEDIR=$(dirname "$0")

java -Xms256m -Xmx512m -jar "$BASEDIR"/lib/device-simulator.jar "$@"
