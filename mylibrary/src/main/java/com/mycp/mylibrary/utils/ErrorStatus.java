package com.mycp.mylibrary.utils;

public interface ErrorStatus {
    int CLIENT_PROTOCOL_ERROR = 508;
    int IO_ERROR = 510;
    int JSON_PARSE_ERROR = 511;
    int OK = 200;
    int REQUEST_FAILED = 512;
    int UNSUPPORTED_ENCODING = 509;
    int NETWORK_ERROR = -5;
}
