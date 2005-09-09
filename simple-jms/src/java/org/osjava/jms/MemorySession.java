package org.osjava.jms;

import java.io.Serializable;
import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TemporaryQueue;
import javax.jms.TemporaryTopic;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

public class MemorySession implements Session {

    private boolean transacted;
    private int acknowledgeMode;
    private MessageListener listener;

    public MemorySession(boolean transacted, int acknowledgeMode) {
        this.transacted = transacted;
        this.acknowledgeMode = acknowledgeMode;
    }

    public BytesMessage createBytesMessage() throws JMSException {
        return new MemoryBytesMessage();
    }

    public MapMessage createMapMessage() throws JMSException {
        return new MemoryMapMessage();
    }

    public Message createMessage() throws JMSException {
        return new MemoryMessage();
    }

    public ObjectMessage createObjectMessage() throws JMSException {
        return new MemoryObjectMessage();
    }

    public ObjectMessage createObjectMessage(Serializable ser) throws JMSException {
        ObjectMessage msg = new MemoryObjectMessage();
        msg.setObject(ser);
        return msg;
    }

    public StreamMessage createStreamMessage() throws JMSException {
        return new MemoryStreamMessage();
    }

    public TextMessage createTextMessage() throws JMSException {
        return new MemoryTextMessage();
    }

    public TextMessage createTextMessage(String text) throws JMSException {
        TextMessage msg = new MemoryTextMessage();
        msg.setText(text);
        return msg;
    }

    public boolean getTransacted() throws JMSException {
        return this.transacted;
    }

    public int getAcknowledgeMode() throws JMSException {
        return this.acknowledgeMode;
    }

    public void commit() throws JMSException {
        // TODO: Implement this
    }

    public void rollback() throws JMSException {
        // TODO: Implement this
    }

    public void close() throws JMSException {
        // TODO: Implement this
    }

    public void recover() throws JMSException {
        // TODO: Implement this
    }

    public MessageListener getMessageListener() throws JMSException {
        return this.listener;
    }

    public void setMessageListener(MessageListener listener) throws JMSException {
        this.listener = listener;
    }

    public void run() {
        // TODO: Implement this
    }

    public MessageProducer createProducer(Destination destination) throws JMSException {
        return new MemoryMessageProducer(destination);
    }

    public MessageConsumer createConsumer(Destination destination) throws JMSException {
        return new MemoryMessageConsumer(destination);
    }

    public MessageConsumer createConsumer(Destination destination, String messageSelector) throws JMSException {
        return new MemoryMessageConsumer(destination, messageSelector);
    }

    public MessageConsumer createConsumer(Destination destination, String messageSelector, boolean noLocal) throws JMSException {
        return new MemoryMessageConsumer(destination, messageSelector, noLocal);
    }

    public Queue createQueue(String name) throws JMSException {
        return new MemoryQueue(name);
    }

    public Topic createTopic(String name) throws JMSException {
        return new MemoryTopic(name);
    }

    public TopicSubscriber createDurableSubscriber(Topic topic, String name) throws JMSException {
        return new MemoryTopicSubscriber(topic, name);
    }

    public TopicSubscriber createDurableSubscriber(Topic topic, String name, String messageSelector, boolean noLocal) throws JMSException {
        return new MemoryTopicSubscriber(topic, name, messageSelector, noLocal);
    }

    public QueueBrowser createBrowser(Queue queue) throws JMSException {
        return new MemoryQueueBrowser(queue);
    }

    public QueueBrowser createBrowser(Queue queue, String messageSelector) throws JMSException {
        return new MemoryQueueBrowser(queue, messageSelector);
    }

    public TemporaryQueue createTemporaryQueue() throws JMSException {
        return new MemoryTemporaryQueue("TODO: Temporary name?");
    }

    public TemporaryTopic createTemporaryTopic() throws JMSException {
        return new MemoryTemporaryTopic("TODO: Temporary name?");
    }

    public void unsubscribe(String name) throws JMSException {
        // TODO: Implement this
    }

}
