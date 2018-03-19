package me.paullicata.serverconnect;

/*
 * Copyright (c) 2010-2018 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.Map;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

/** This example demonstrates how to create a websocket connection to a server. Only the most important callbacks are overloaded. */
public class WSClient extends WebSocketClient {


	ByteBuffer byteMessage;
	String stringMessage;

	public WSClient(URI serverUri , Draft draft ) {
		super( serverUri, draft );
	}

	public WSClient(URI serverURI ) {
		super( serverURI );
	}

	public WSClient(URI serverUri, Map<String, String> httpHeaders ) {
		super(serverUri, httpHeaders);
	}

	@Override
	public void onOpen( ServerHandshake handshakedata ) {



		/*send("Hello, it is me. Mario :)");
		System.out.println( "opened connection" );
		*/
		// if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
	}


	//TODO: give message to the client to work with
	/*
		1st message should be image in bytes

		The rest should be made up of instructions to the user


	*/
	@Override
	public void onMessage( String message ) {

		this.stringMessage = message;


		System.out.println( "received: " + message );
	}

	@Override
	public void onMessage( ByteBuffer message ) {

		this.byteMessage = message;

		System.out.println( "received: " + message );
	}

	@Override
	public void onClose( int code, String reason, boolean remote ) {
		// The codecodes are documented in class org.java_websocket.framing.CloseFrame
		System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) + " Code: " + code + " Reason: " + reason );
	}

	@Override
	public void onError( Exception ex ) {
		ex.printStackTrace();
		// if the error is fatal then onClose will be called additionally
	}





	/*


	If this is not working try using this guide:
	https://github.com/TooTallNate/Java-WebSocket/blob/master/src/main/example/ChatClient.java
	I think this will work better, this ovverrides the class in the file, should be able to
	add to the drawing queue much easier

	//HOW TO USE:




	public static void main( String[] args ) throws URISyntaxException {
		WSClient c = new WSClient( new URI( "ws://proj-309-sb-2.cs.iastate.edu:8080/board/{BoardID}" )); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
		c.connect();
		c.onm

	}

	*/


}