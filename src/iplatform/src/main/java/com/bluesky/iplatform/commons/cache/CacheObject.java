package com.bluesky.iplatform.commons.cache;

public interface CacheObject extends Cloneable {

	public Object clone();

	public boolean isDeleted();

	public void setDeleted(boolean paramBoolean);

	public boolean isModified();

	public void setModified(boolean paramBoolean);

	public boolean isNew();

	public void setNew(boolean paramBoolean);

}
