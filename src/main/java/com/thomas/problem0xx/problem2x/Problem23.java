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
package com.thomas.problem0xx.problem2x;

import static com.thomas.util.NumberUtils.sumsOfProperDivisors;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 06.11.2009
 */
public class Problem23 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 31.12.2009
     */
    @Override
    public Integer solve() {

        final int[] sumOfProperDivisors = sumsOfProperDivisors(28111 + 1); // 28123 - 12
        
        int sum = 276;

        for (int i = 25; i <= 28123; ++i) {
            if (!isSumOfAbundant(i, sumOfProperDivisors)) {
                sum += i;
            }
        }

        return sum;
    }
    
    private boolean isSumOfAbundant(int n, int[] sumOfProperDivisors) {
        
        for (int s = 12; 2 * s <= n; ++s) {
            if ((sumOfProperDivisors[s] > s) && (sumOfProperDivisors[n - s] > n - s)) return true;
        }
        
        return false;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 06.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem23());
    }

}
