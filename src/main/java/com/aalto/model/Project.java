
package com.aalto.model;

import java.io.Serializable;

public class Project implements Serializable {

	private Long pid;
	private String name;

	public Project(String name) {
		this.name = name;
	}

	/**
	 * Returns the pid.
	 *
	 * @return Returns the pid
	 */
	public Long getPid() {
		return pid;
	}

	/**
	 * @param pid The id to set.
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}

	/**
	 * Returns the name.
	 *
	 * @return Returns the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
        
        @Override
        public String toString() {
            return "Project to string";
        }

}
