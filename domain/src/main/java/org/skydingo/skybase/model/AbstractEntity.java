/* 
 * AbstractEntity.java
 * 
 * Copyright 2011-2012 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.skydingo.skybase.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@NodeEntity
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractEntity<T extends Entity<T>> implements Entity<T> {
	@GraphId private Long id;

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.Entity#getId()
	 */
	@Override
	@XmlAttribute
	public Long getId() { return id; }

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.Entity#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) { this.id = id; }

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(T that) {
		return getDisplayName().compareTo(that.getDisplayName());
	}

}
