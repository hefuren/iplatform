package com.bluesky.iplatform.commons.hierarchy;

import java.io.Serializable;

public interface Hierarchyable extends Serializable {

	public Integer getId();

	public Integer getParentID();

	public void setParentID(Integer parentID);

	public String getName();

}
