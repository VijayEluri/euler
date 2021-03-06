/**
 * $Id$
 *
 * Copyright (c) 2009 Thomas Beckmann 
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
package com.thomas.problem114;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 16.12.2009
 */
public class Problem114 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 01.04.2010
     */
    @Override
    public Long solve() {

        final long[] red = new long[50 + 1];
        final long[] black = new long[50 + 1];

        long ret = addRed(50, red, black) + addBlack(50, red, black);
        
        return ret;
    }

    private long addRed(int len, long[] red, long[] black) {
        
        if (len < 0) return 0;
        if (len == 0) return 1;
        
        if (red[len] == 0) {
            for (int n = 3; n <= len; ++n) {
                red[len] += addBlack(len - n, red, black);
            }
        }
        return red[len];
    }

    private long addBlack(int len, long[] red, long[] black) {
        
        if (len < 0) return 0;
        if (len == 0) return 1;
        
        if (black[len] == 0) {
            for (int n = 1; n <= len; ++n) {
                black[len] += addRed(len - n, red, black);
            }
        }
        return black[len];
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 16.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem114());
    }

}
