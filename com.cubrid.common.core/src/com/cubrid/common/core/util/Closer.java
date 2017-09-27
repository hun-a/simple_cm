package com.cubrid.common.core.util;

import static com.cubrid.common.core.util.NoOp.noOp;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Selector;
import java.sql.Connection;
import java.sql.SQLException;

public final class Closer {
	private Closer() {
		noOp();
	}

	/**
	 * close closeable object silently (will eat off exception, print to error
	 * stream)
	 *
	 * @param co closeable object, can be null.
	 */
	public static void close(Closeable co) {
		if (co == null) {
			return;
		}

		try {
			co.close();
		} catch (IOException e) {
			noOp();
		}
	}

	/**
	 * close socket silently (will eat off exception, print to error stream)
	 *
	 * @param sock socket object, can be null.
	 */
	public static void close(Socket sock) {
		if (sock == null) {
			return;
		}

		try {
			sock.close();
		} catch (IOException e) {
			noOp();
		}
	}

	/**
	 * close server socket silently (will eat off exception, print to error
	 * stream)
	 *
	 * @param sock server socket object, can be null.
	 */
	public static void close(ServerSocket sock) {
		if (sock == null) {
			return;
		}

		try {
			sock.close();
		} catch (IOException e) {
			noOp();
		}
	}

	/**
	 * close datagram socket silently (will eat off exception, print to error
	 * stream)
	 *
	 * @param sock datagram socket object, can be null.
	 */
	public static void close(DatagramSocket sock) {
		if (sock == null) {
			return;
		}

		sock.close();
	}

	/**
	 * close selector silently (will eat off exception, print to error stream)
	 *
	 * @param selector selector object, can be null
	 */
	public static void close(Selector selector) {

		if (selector == null) {
			return;
		}

		try {
			selector.close();
		} catch (IOException e) {
			noOp();
		}
	}

	/**
	 * close database connection silently (will eat off exception, print to
	 * error stream)
	 *
	 * @param conn db connection
	 */
	public static void close(Connection conn) {
		if (conn == null) {
			return;
		}

		try {
			conn.close();
		} catch (SQLException e) {
			noOp();
		}
	}
}
