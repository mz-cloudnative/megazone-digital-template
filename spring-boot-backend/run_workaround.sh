#!/bin/sh
sed -i '/KAFKA_ZOOKEEPER_CONNECT/d' /etc/confluent/docker/configure

sed -i 's/cub zk-ready/echo ignore zk-ready/' /etc/confluent/docker/ensure

echo "kafka-storage format --ignore-formatted -t $UUID -c /etc/kafka/kafka.properties">> /etc/confluent/docker/ensure
#echo "kafka-storage format --ignore-formatted -t PW8YNrLGSJKjlMwLY6fo4A -c /etc/kafka/kafka.properties">> /etc/confluent/docker/ensure
