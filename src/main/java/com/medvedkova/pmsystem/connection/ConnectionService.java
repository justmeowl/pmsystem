package com.medvedkova.pmsystem.connection;

import com.medvedkova.pmsystem.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class ConnectionService {

    private final ConnectionRepository connectionRepository;

    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    private Set<Connection> getConnections(Task task, String connectionsText) {
        Set<Connection> connections = new HashSet<>();

        for (String connectionText : connectionsText.split("\\D+")) {
            Connection connection = new Connection(Integer.parseInt(connectionText), task);
            connections.add(connection);
        }
        return connections;
    }

    public void editConnection(Task task, String connectionsText) {
        deleteConnectionByTask(task);
        Set<Connection> newConnections = getConnections(task, connectionsText);
        boolean isCorrectInputPreviousNumber = !newConnections.isEmpty()
                && !task.getConnectionSet().containsAll(newConnections);
        if (isCorrectInputPreviousNumber) {
            newConnections.forEach(task::addConnection);
        }
    }

    private void deleteConnectionByTask(Task task) {
        connectionRepository.deleteAllByTask(task);
    }
}
