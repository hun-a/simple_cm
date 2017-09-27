/*
 * Copyright (C) 2015 Search Solution Corporation. All rights reserved by Search
 * Solution.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: -
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. - Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. - Neither the name of the <ORGANIZATION> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */
package com.nhn.dbtool.query.parser.sqlmap.model;

import java.util.List;

/**
 * A definition of isLessEqual tag.
 * <isLessEqual property="" prepend="" open="" close="" removeFirstPrepend="true" compareProperty="" compareValue=""></isLessEqual>
 *
 * @author Bumsik, Jang
 */
public class IsLessEqualTag extends SqlMapCondition {
	private static final long serialVersionUID = 7912248936308627959L;

	public IsLessEqualTag() {
		this.setType("isLessEqual");
	}

	@Override
	public String getExpectedCompareValue() {
		return compareValue != null ? "<= " + compareValue
				: (compareProperty != null ? "<= #" + compareProperty + "#" : null);
	}

	@Override
	public boolean isMatchCondition(List<String> parameterList) {
		for (String parameter : parameterList) {
			if (parameter.startsWith(getProperty())) {
				String[] value = parameter.split(":");
				try {
					if (value.length == 1 || (value.length == 2
							&& (value[1].equals(this.getExpectedCompareValue())
							|| Double.parseDouble(value[1]) <= Double.parseDouble(this.compareValue)))) {
						return true;
					}
				} catch (Exception e) {
					// ignore
				}
			}
		}

		return false;
	}
}
