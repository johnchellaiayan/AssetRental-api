package com.assetmgmt.util;

public class Constants {
	public static final String CACHE_CONTROL_AGE = "public, max-age=31536000";

	public final class HeaderValue {
		private HeaderValue() {
		}

		public static final String AUTHORIZATION = "Authorization";
		public static final String REFRESH_TOKEN = "refresh_token";
		public static final String GROUP = "group";
		public static final String CONTENT_TYPE = "Content-Type";

	}

	public final class BooleanValue {
		private BooleanValue() {
		}

		public static final String IS_OTP_VALID = "isOtpValid";
		public static final String IS_ERROR_HANDLED = "isErrorHandled";
		public static final String IS_API_KEY_INVALID = "isApiKeyInvalid";
	}
}