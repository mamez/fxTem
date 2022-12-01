package com.grupobbva.col.rest.conector.contract;

public class LogImpl implements LogConector {

	@Override
	public void debug(String msg) {
		System.out.println(msg);

	}

	@Override
	public void error(String msg) {
		System.out.println(msg);

	}

}
