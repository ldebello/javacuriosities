package ar.com.javacuriosities.nio.server.http.util;

public class HttpHeaders {

	public static int HTTP_METHOD_GET = 1;
	public static int HTTP_METHOD_POST = 2;
	public static int HTTP_METHOD_PUT = 3;
	public static int HTTP_METHOD_HEAD = 4;
	public static int HTTP_METHOD_DELETE = 5;

	public int httpMethod = 0;

	public int hostStartIndex = 0;
	public int hostEndIndex = 0;

	public int contentLength = 0;

	public int bodyStartIndex = 0;
	public int bodyEndIndex = 0;
}