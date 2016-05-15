/**
 * $Id$
 *
 * Copyright (c) 2014 Thomas Beckmann
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.thomas.problem4xx.problem46x;

import java.util.Locale;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 21.05.2014
 */
public class Problem469 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public String solve() {

        long N = 22;

        return String.format(Locale.ENGLISH, "%.14f", E(N) / N);
    }

    double E(long n) {
        
        if (n < 4) return (n-1);
        
        double E = 0;
        
        for (long i = 2; i < (n-1); ++i) {
            E += E(i);
        }
        
        return 2*E / (n-3);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem469());
    }

}