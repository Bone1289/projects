#KAFKA Commands
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic t_hello --partitions 1 --replication-factor 1 <br>
kafka-topics.sh --bootstrap-server localhost:9092 --list <br>
kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic t_hello <br>