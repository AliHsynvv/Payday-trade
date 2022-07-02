package com.company.paydaytrade.dto;
public class ResponseDto {

        private Integer errorCode;
        private String errorMessage;
        private String succesMessage;
        private Object obj;

        private ResponseDto() {
        }

        public static ResponseDto of(Object obj) {
            ResponseDto resp = new ResponseDto();
            resp.setObj(obj);
            return resp;
        }

        public static ResponseDto of(Object obj, String succesMessage) {
            ResponseDto resp = new ResponseDto();
            resp.setObj(obj);
            resp.setSuccesMessage(succesMessage);
            return resp;
        }

        public static ResponseDto of(Object obj, Integer errorCode, String errorMessage) {
            ResponseDto resp = new ResponseDto();
            resp.setObj(obj);
            resp.setErrorMessage(errorMessage);
            resp.setErrorCode(errorCode);
            return resp;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getSuccesMessage() {
            return succesMessage;
        }

        public void setSuccesMessage(String succesMessage) {
            this.succesMessage = succesMessage;
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }
}
