/*
 * Copyright (C) 2018 European Spallation Source ERIC.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package org.phoebus.applications.saveandrestore.model;

import org.epics.vtype.VType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.phoebus.applications.saveandrestore.model.json.VTypeDeserializer;
import org.phoebus.applications.saveandrestore.model.json.VTypeSerializer;


/**
 * Class encapsulating a {@link VType} holding the PV data. 
 * @author georgweiss
 * Created 28 Nov 2018
 */
public class SnapshotItem {
	private int snapshotId;

	/**
	 * The {@link ConfigPv} associated with this {@link SnapshotItem}. The PV name
	 * and provider identity are determined from this.
	 */
	private ConfigPv configPv;
	
	/**
	 * The actual PV data, including alarms and time stamps.
	 */
	@JsonSerialize(using = VTypeSerializer.class)
	@JsonDeserialize(using = VTypeDeserializer.class)
	private VType value;
	
	/**
	 * The actual read-back PV data, including alarms and time stamps.
	 */
	@JsonSerialize(using = VTypeSerializer.class)
	@JsonDeserialize(using = VTypeDeserializer.class)
	private VType readbackValue;

	public int getSnapshotId() {
		return snapshotId;
	}

	public void setSnapshotId(int snapshotId) {
		this.snapshotId = snapshotId;
	}

	public ConfigPv getConfigPv() {
		return configPv;
	}

	public void setConfigPv(ConfigPv configPv) {
		this.configPv = configPv;
	}

	public VType getValue() {
		return value;
	}

	public void setValue(VType value) {
		this.value = value;
	}

	public VType getReadbackValue() {
		return readbackValue;
	}

	public void setReadbackValue(VType readbackValue) {
		this.readbackValue = readbackValue;
	}

	@Override
	public String toString() {
		return new StringBuffer()
				.append("value=")
				.append(value != null ? value.toString() : "READ FAILED")
				.append(", config pv=").append(configPv.toString())
				.append(readbackValue != null ? (", readback pv=" + readbackValue.toString()) : (", readback pv=READ_FAILED"))
				.toString();
	}

	public static Builder builder(){
		return new Builder();
	}

	public static class Builder{

		private SnapshotItem snapshotItem;

		private Builder(){
			snapshotItem = new SnapshotItem();
		}

		public Builder snapshotId(int snapshotId){
			snapshotItem.setSnapshotId(snapshotId);
			return this;
		}

		public Builder configPv(ConfigPv configPv){
			snapshotItem.setConfigPv(configPv);
			return this;
		}

		public Builder value(VType value){
			snapshotItem.setValue(value);
			return this;
		}

		public Builder readbackValue(VType readbackValue){
			snapshotItem.setReadbackValue(readbackValue);
			return this;
		}

		public SnapshotItem build(){
			return snapshotItem;
		}
	}
}
