package com.example.lenovo.myapplication;


import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * 解析XML数据的Volley
 * Created by 李冉 on 2015-08-25.
 */
public class XMLRequest extends Request<XmlPullParser> {

    private final Response.Listener<XmlPullParser> mListener;

    public XMLRequest(int Method, String url, Response.ErrorListener listener, Response.Listener<XmlPullParser> Listener) {
        super(Method, url, listener);
        this.mListener = Listener;
    }

    public XMLRequest(String url, Response.ErrorListener listener, Response.Listener<XmlPullParser> Listener) {
        super(Method.GET, url, listener);
        this.mListener = Listener;
    }


    @Override
    protected Response<XmlPullParser> parseNetworkResponse(NetworkResponse response) {
        try {
            String xmlString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlString));
            return Response.success(xmlPullParser, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (XmlPullParserException e) {
            return Response.error(new ParseError(e));
        }
    }


    @Override
    protected void deliverResponse(XmlPullParser xmlPullParser) {
        mListener.onResponse(xmlPullParser);
    }
}
