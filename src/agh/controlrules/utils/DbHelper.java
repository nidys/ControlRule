package agh.controlrules.utils;

import java.util.List;

import agh.controlrules.db.DatabaseManager;
import agh.controlrules.db.queries.tables.QueryCreator;

public class DbHelper {
	public int insert(QueryCreator query) {
		return DatabaseManager.insert(query);
	}

	public List<QueryCreator> select(QueryCreator query) {
		return DatabaseManager.select(query);
	}
}
