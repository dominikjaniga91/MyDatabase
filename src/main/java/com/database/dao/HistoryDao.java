package com.database.dao;

import com.database.model.History;

import java.util.List;

public interface HistoryDao {

    void addToHistory(String user, String employee, String action);

    List<History> getFromHistory();

}
