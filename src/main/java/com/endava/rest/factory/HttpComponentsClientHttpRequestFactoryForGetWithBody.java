package com.endava.rest.factory;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.net.URI;

public class HttpComponentsClientHttpRequestFactoryForGetWithBody extends HttpComponentsClientHttpRequestFactory {

    private static final class HttpGetRequestWithBody extends HttpEntityEnclosingRequestBase {
        public HttpGetRequestWithBody(URI uri) {
            super.setURI(uri);
        }

        @Override
        public String getMethod() {
            return HttpMethod.GET.name();
        }
    }

    @Override
    protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
        if (HttpMethod.GET.equals(httpMethod)) {
            return new HttpGetRequestWithBody(uri);
        }
        return super.createHttpUriRequest(httpMethod, uri);
    }
}
