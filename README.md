# rikit

Rikit is a java based application that implement Chord DHT [1] with WebSocket.

# Main feature:
1. Using WebSocket as the transport layer. 
2. It allows other application to store an retrieve data. 
3. Data is replicated and distributed within a DHT.
4. Data can be stored in on-heap memory, off-heap memory or disk.
5. Allow data overflow to another DHT.

# Requirements
1. Java 7
2. Netty
3. Jackson
4. MapDB