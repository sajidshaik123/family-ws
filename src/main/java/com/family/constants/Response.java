package com.family.constants;

import java.util.Objects;

public class Response {

	private Object data;

	private Status status;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Response() {

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static class Status {

		private String success;

		private String message;

		public Status() {

		}

		public String getSuccess() {
			return success;
		}

		public void setSuccess(String success) {
			this.success = success;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Status status = (Status) o;
			return Objects.equals(success, status.success) && Objects.equals(message, status.message);
		}

		@Override
		public int hashCode() {
			return Objects.hash(success, message);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Response that = (Response) o;
		return Objects.equals(data, that.data) && Objects.equals(status, that.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, status);
	}
}
