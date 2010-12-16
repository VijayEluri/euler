/**
 * $Id$
 *
 * Copyright (c) 2010 Thomas Beckmann
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
package com.thomas.problem302;

import static com.thomas.util.NumberUtils.pow;
import static com.thomas.util.NumberUtils.*;
import static com.thomas.util.PrimeUtils.primes;
import static java.lang.Math.cbrt;
import static java.lang.Math.ceil;
import static java.lang.Math.log;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 04.12.2010
 */
public class Problem302 implements Problem {

    private static final int[][] GCD = {{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
        {2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,},
        {3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,},
        {4,1,2,1,4,1,2,1,4,1,2,1,4,1,2,1,4,1,2,1,4,1,2,1,4,1,2,1,4,1,2,1,4,1,2,1,4,1,2,1,4,1,2,1,4,1,2,1,4,1,2,1,4,1,2,1,4,1,2,1,4,1,2,1,},
        {5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,},
        {6,1,2,3,2,1,6,1,2,3,2,1,6,1,2,3,2,1,6,1,2,3,2,1,6,1,2,3,2,1,6,1,2,3,2,1,6,1,2,3,2,1,6,1,2,3,2,1,6,1,2,3,2,1,6,1,2,3,2,1,6,1,2,3,},
        {7,1,1,1,1,1,1,7,1,1,1,1,1,1,7,1,1,1,1,1,1,7,1,1,1,1,1,1,7,1,1,1,1,1,1,7,1,1,1,1,1,1,7,1,1,1,1,1,1,7,1,1,1,1,1,1,7,1,1,1,1,1,1,7,},
        {8,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,},
        {9,1,1,3,1,1,3,1,1,9,1,1,3,1,1,3,1,1,9,1,1,3,1,1,3,1,1,9,1,1,3,1,1,3,1,1,9,1,1,3,1,1,3,1,1,9,1,1,3,1,1,3,1,1,9,1,1,3,1,1,3,1,1,9,},
        {10,1,2,1,2,5,2,1,2,1,10,1,2,1,2,5,2,1,2,1,10,1,2,1,2,5,2,1,2,1,10,1,2,1,2,5,2,1,2,1,10,1,2,1,2,5,2,1,2,1,10,1,2,1,2,5,2,1,2,1,10,1,2,1,},
        {11,1,1,1,1,1,1,1,1,1,1,11,1,1,1,1,1,1,1,1,1,1,11,1,1,1,1,1,1,1,1,1,1,11,1,1,1,1,1,1,1,1,1,1,11,1,1,1,1,1,1,1,1,1,1,11,1,1,1,1,1,1,1,1,},
        {12,1,2,3,4,1,6,1,4,3,2,1,12,1,2,3,4,1,6,1,4,3,2,1,12,1,2,3,4,1,6,1,4,3,2,1,12,1,2,3,4,1,6,1,4,3,2,1,12,1,2,3,4,1,6,1,4,3,2,1,12,1,2,3,},
        {13,1,1,1,1,1,1,1,1,1,1,1,1,13,1,1,1,1,1,1,1,1,1,1,1,1,13,1,1,1,1,1,1,1,1,1,1,1,1,13,1,1,1,1,1,1,1,1,1,1,1,1,13,1,1,1,1,1,1,1,1,1,1,1,},
        {14,1,2,1,2,1,2,7,2,1,2,1,2,1,14,1,2,1,2,1,2,7,2,1,2,1,2,1,14,1,2,1,2,1,2,7,2,1,2,1,2,1,14,1,2,1,2,1,2,7,2,1,2,1,2,1,14,1,2,1,2,1,2,7,},
        {15,1,1,3,1,5,3,1,1,3,5,1,3,1,1,15,1,1,3,1,5,3,1,1,3,5,1,3,1,1,15,1,1,3,1,5,3,1,1,3,5,1,3,1,1,15,1,1,3,1,5,3,1,1,3,5,1,3,1,1,15,1,1,3,},
        {16,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,16,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,16,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,16,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,},
        {17,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,17,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,17,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,17,1,1,1,1,1,1,1,1,1,1,1,1,},
        {18,1,2,3,2,1,6,1,2,9,2,1,6,1,2,3,2,1,18,1,2,3,2,1,6,1,2,9,2,1,6,1,2,3,2,1,18,1,2,3,2,1,6,1,2,9,2,1,6,1,2,3,2,1,18,1,2,3,2,1,6,1,2,9,},
        {19,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,19,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,19,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,19,1,1,1,1,1,1,},
        {20,1,2,1,4,5,2,1,4,1,10,1,4,1,2,5,4,1,2,1,20,1,2,1,4,5,2,1,4,1,10,1,4,1,2,5,4,1,2,1,20,1,2,1,4,5,2,1,4,1,10,1,4,1,2,5,4,1,2,1,20,1,2,1,},
        {21,1,1,3,1,1,3,7,1,3,1,1,3,1,7,3,1,1,3,1,1,21,1,1,3,1,1,3,7,1,3,1,1,3,1,7,3,1,1,3,1,1,21,1,1,3,1,1,3,7,1,3,1,1,3,1,7,3,1,1,3,1,1,21,},
        {22,1,2,1,2,1,2,1,2,1,2,11,2,1,2,1,2,1,2,1,2,1,22,1,2,1,2,1,2,1,2,1,2,11,2,1,2,1,2,1,2,1,2,1,22,1,2,1,2,1,2,1,2,1,2,11,2,1,2,1,2,1,2,1,},
        {23,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,23,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,23,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
        {24,1,2,3,4,1,6,1,8,3,2,1,12,1,2,3,8,1,6,1,4,3,2,1,24,1,2,3,4,1,6,1,8,3,2,1,12,1,2,3,8,1,6,1,4,3,2,1,24,1,2,3,4,1,6,1,8,3,2,1,12,1,2,3,},
        {25,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,25,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,5,1,1,1,1,25,1,1,1,1,5,1,1,1,1,5,1,1,1,},
        {26,1,2,1,2,1,2,1,2,1,2,1,2,13,2,1,2,1,2,1,2,1,2,1,2,1,26,1,2,1,2,1,2,1,2,1,2,1,2,13,2,1,2,1,2,1,2,1,2,1,2,1,26,1,2,1,2,1,2,1,2,1,2,1,},
        {27,1,1,3,1,1,3,1,1,9,1,1,3,1,1,3,1,1,9,1,1,3,1,1,3,1,1,27,1,1,3,1,1,3,1,1,9,1,1,3,1,1,3,1,1,9,1,1,3,1,1,3,1,1,27,1,1,3,1,1,3,1,1,9,},
        {28,1,2,1,4,1,2,7,4,1,2,1,4,1,14,1,4,1,2,1,4,7,2,1,4,1,2,1,28,1,2,1,4,1,2,7,4,1,2,1,4,1,14,1,4,1,2,1,4,7,2,1,4,1,2,1,28,1,2,1,4,1,2,7,},
        {29,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,29,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,29,1,1,1,1,1,},
        {30,1,2,3,2,5,6,1,2,3,10,1,6,1,2,15,2,1,6,1,10,3,2,1,6,5,2,3,2,1,30,1,2,3,2,5,6,1,2,3,10,1,6,1,2,15,2,1,6,1,10,3,2,1,6,5,2,3,2,1,30,1,2,3,},
        {31,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,31,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,31,1,},
        {32,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,16,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,32,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,16,1,2,1,4,1,2,1,8,1,2,1,4,1,2,1,},
        {33,1,1,3,1,1,3,1,1,3,1,11,3,1,1,3,1,1,3,1,1,3,11,1,3,1,1,3,1,1,3,1,1,33,1,1,3,1,1,3,1,1,3,1,11,3,1,1,3,1,1,3,1,1,3,11,1,3,1,1,3,1,1,3,},
        {34,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,17,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,34,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,17,2,1,2,1,2,1,2,1,2,1,2,1,},
        {35,1,1,1,1,5,1,7,1,1,5,1,1,1,7,5,1,1,1,1,5,7,1,1,1,5,1,1,7,1,5,1,1,1,1,35,1,1,1,1,5,1,7,1,1,5,1,1,1,7,5,1,1,1,1,5,7,1,1,1,5,1,1,7,},
        {36,1,2,3,4,1,6,1,4,9,2,1,12,1,2,3,4,1,18,1,4,3,2,1,12,1,2,9,4,1,6,1,4,3,2,1,36,1,2,3,4,1,6,1,4,9,2,1,12,1,2,3,4,1,18,1,4,3,2,1,12,1,2,9,},
        {37,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,37,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
        {38,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,19,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,38,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,19,2,1,2,1,2,1,},
        {39,1,1,3,1,1,3,1,1,3,1,1,3,13,1,3,1,1,3,1,1,3,1,1,3,1,13,3,1,1,3,1,1,3,1,1,3,1,1,39,1,1,3,1,1,3,1,1,3,1,1,3,13,1,3,1,1,3,1,1,3,1,1,3,},
        {40,1,2,1,4,5,2,1,8,1,10,1,4,1,2,5,8,1,2,1,20,1,2,1,8,5,2,1,4,1,10,1,8,1,2,5,4,1,2,1,40,1,2,1,4,5,2,1,8,1,10,1,4,1,2,5,8,1,2,1,20,1,2,1,},
        {41,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,41,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
        {42,1,2,3,2,1,6,7,2,3,2,1,6,1,14,3,2,1,6,1,2,21,2,1,6,1,2,3,14,1,6,1,2,3,2,7,6,1,2,3,2,1,42,1,2,3,2,1,6,7,2,3,2,1,6,1,14,3,2,1,6,1,2,21,},
        {43,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,43,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
        {44,1,2,1,4,1,2,1,4,1,2,11,4,1,2,1,4,1,2,1,4,1,22,1,4,1,2,1,4,1,2,1,4,11,2,1,4,1,2,1,4,1,2,1,44,1,2,1,4,1,2,1,4,1,2,11,4,1,2,1,4,1,2,1,},
        {45,1,1,3,1,5,3,1,1,9,5,1,3,1,1,15,1,1,9,1,5,3,1,1,3,5,1,9,1,1,15,1,1,3,1,5,9,1,1,3,5,1,3,1,1,45,1,1,3,1,5,3,1,1,9,5,1,3,1,1,15,1,1,9,},
        {46,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,23,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,46,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,},
        {47,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,47,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
        {48,1,2,3,4,1,6,1,8,3,2,1,12,1,2,3,16,1,6,1,4,3,2,1,24,1,2,3,4,1,6,1,16,3,2,1,12,1,2,3,8,1,6,1,4,3,2,1,48,1,2,3,4,1,6,1,8,3,2,1,12,1,2,3,},
        {49,1,1,1,1,1,1,7,1,1,1,1,1,1,7,1,1,1,1,1,1,7,1,1,1,1,1,1,7,1,1,1,1,1,1,7,1,1,1,1,1,1,7,1,1,1,1,1,1,49,1,1,1,1,1,1,7,1,1,1,1,1,1,7,},
        {50,1,2,1,2,5,2,1,2,1,10,1,2,1,2,5,2,1,2,1,10,1,2,1,2,25,2,1,2,1,10,1,2,1,2,5,2,1,2,1,10,1,2,1,2,5,2,1,2,1,50,1,2,1,2,5,2,1,2,1,10,1,2,1,},
        {51,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,17,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,17,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,51,1,1,3,1,1,3,1,1,3,1,1,3,},
        {52,1,2,1,4,1,2,1,4,1,2,1,4,13,2,1,4,1,2,1,4,1,2,1,4,1,26,1,4,1,2,1,4,1,2,1,4,1,2,13,4,1,2,1,4,1,2,1,4,1,2,1,52,1,2,1,4,1,2,1,4,1,2,1,},
        {53,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,53,1,1,1,1,1,1,1,1,1,1,},
        {54,1,2,3,2,1,6,1,2,9,2,1,6,1,2,3,2,1,18,1,2,3,2,1,6,1,2,27,2,1,6,1,2,3,2,1,18,1,2,3,2,1,6,1,2,9,2,1,6,1,2,3,2,1,54,1,2,3,2,1,6,1,2,9,},
        {55,1,1,1,1,5,1,1,1,1,5,11,1,1,1,5,1,1,1,1,5,1,11,1,1,5,1,1,1,1,5,1,1,11,1,5,1,1,1,1,5,1,1,1,11,5,1,1,1,1,5,1,1,1,1,55,1,1,1,1,5,1,1,1,},
        {56,1,2,1,4,1,2,7,8,1,2,1,4,1,14,1,8,1,2,1,4,7,2,1,8,1,2,1,28,1,2,1,8,1,2,7,4,1,2,1,8,1,14,1,4,1,2,1,8,7,2,1,4,1,2,1,56,1,2,1,4,1,2,7,},
        {57,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,19,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,19,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,57,1,1,3,1,1,3,},
        {58,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,29,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,58,1,2,1,2,1,},
        {59,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,59,1,1,1,1,},
        {60,1,2,3,4,5,6,1,4,3,10,1,12,1,2,15,4,1,6,1,20,3,2,1,12,5,2,3,4,1,30,1,4,3,2,5,12,1,2,3,20,1,6,1,4,15,2,1,12,1,10,3,4,1,6,5,4,3,2,1,60,1,2,3,},
        {61,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,61,1,1,},
        {62,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,31,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,62,1,},
        {63,1,1,3,1,1,3,7,1,9,1,1,3,1,7,3,1,1,9,1,1,21,1,1,3,1,1,9,7,1,3,1,1,3,1,7,9,1,1,3,1,1,21,1,1,9,1,1,3,7,1,3,1,1,9,1,7,3,1,1,3,1,1,63,},};
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object solve() {

        final long max = pow(10L, 18);

        long count = 0;

        final long[] primes = toLongArray(primes((int)ceil(cbrt(max/4))));
        
        for (int i = primes.length; i-- > 0; ) {
            //System.out.println(primes[i]);
            long current = primes[i] * primes[i];
            
            for (int p = 3, mp = (int)ceil(log(max) / log(primes[i])); p < mp; ++p) {
                count += find(primes, i, max, current *= primes[i], primes[i] - 1, p, p - 1);
            }
        }

        return count;
    }

    private long find(final long[] primes, int index, final long max, final long val, long tot, int gcd, int maxPower) {

        final double q = log((double)max / val);
        
        long count = 0;
        
        for (int i = 0; i < index; ++i) {
            
            final int mp = (int)ceil(q / log(primes[i]));
            
            if (mp < 3) break;

            long current = val * primes[i];
            long totient = tot * (primes[i] - 1);
            for (int p = 2; p < mp; ++p) {
                
                final int ngcd = GCD[gcd][p];
                
                totient *= primes[i];

                if (ngcd == 1 && isAchillesNumber(totient, maxPower, primes)) {
                    ++count;
                }
                count += find(primes, i, max, current *= primes[i], totient, ngcd, maxPower);
            }
        }
        
        return count;
    }
    
    private boolean isAchillesNumber(long n, int gcd, long[] primes) {

        for (int i = 0; n > 1; ++i) {
            long p = primes[i];
            if (n % p == 0) {
                int count = 0;
                do {
                    ++count;
                } while ((n /= p) % p == 0);
                if (count < 2) return false;
                gcd = GCD[gcd][count];
            }
        }

        return gcd == 1;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem302());
    }

}