/*
 *    Copyright 2016 Jeroen van Erp <jeroen@hierynomus.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.hierynomus.asn1.types.primitive;

import com.hierynomus.asn1.ASN1Parser;
import com.hierynomus.asn1.types.ASN1Tag;
import com.hierynomus.asn1.util.Checks;

public class ASN1Boolean extends ASN1PrimitiveValue<Boolean> {

    private boolean value;

    public ASN1Boolean(boolean value) {
        super(ASN1Tag.BOOLEAN);
        this.value = value;
    }

    private ASN1Boolean(byte[] valueBytes, boolean value) {
        super(ASN1Tag.BOOLEAN, valueBytes);
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    protected int valueHash() {
        return value ? 1231 : 1237;
    }

    public static class Parser implements ASN1Parser<ASN1Boolean> {
        @Override
        public ASN1Boolean parse(byte[] value) {
            Checks.checkState(value.length == 1, "Value of ASN1Boolean should have length 1, but was %s", value.length);
            return new ASN1Boolean(value, value[0] != 0x0);
        }
    }
}
