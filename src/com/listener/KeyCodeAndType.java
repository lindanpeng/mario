package com.listener;

public class KeyCodeAndType {
//键盘对应码
private int code;
//类型 0为press，1为release
private int type;
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + code;
	result = prime * result + type;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	KeyCodeAndType other = (KeyCodeAndType) obj;
	if (code != other.code)
		return false;
	if (type != other.type)
		return false;
	return true;
}

}
