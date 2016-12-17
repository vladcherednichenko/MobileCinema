package com.androidbuts.jsonparsing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {

        @SerializedName("response")
        @Expose
        private String response;

        /**
         * @return The response
         */
        public String getResponse() {
            return response;
        }

        /**
         * @param response The response
         */
        public void setResponse(String response) {
            this.response = response;
        }
}
