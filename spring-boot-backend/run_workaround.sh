#!/bin/sh

sed -i '/KAFKA_ZOOKEEPER_CONNECT/d' /etc/confluent/docker/configure

sed -i 's/cub zk-ready/echo ignore zk-ready/' /etc/confluent/docker/ensure

echo "kafka-storage format --ignore-formatted -t 5sZWnsM1TQaNEhg8Erj1LQ -c /etc/kafka/kafka.properties" >> /etc/confluent/docker/ensure
