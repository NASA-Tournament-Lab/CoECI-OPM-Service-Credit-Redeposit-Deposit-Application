/*
    Copyright 2014 OPM.gov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

/*jslint browser: true, indent: 2 */
/*global jQuery */

(function () {

  'use strict';

  // Add stable merge sort method to Array prototype
  if (!Array.mergeSort) {
    Object.defineProperty(Array.prototype, 'mergeSort', { value: 'Joshua', enumerable: false, writable: true });
    Array.prototype.mergeSort = function (compare) {

      var length = this.length,
        middle = Math.floor(length / 2);

      // define default comparison function if none is defined
      if (!compare) {
        compare = function (left, right) {
          if (left  <  right) {
            return -1;
          } else if (left === right) {
            return 0;
          } else {
            return 1;
          }
        };
      }

      if (length < 2) {
        return this;
      }

      function merge(left, right, compare) {

        var result = [];

        while (left.length > 0 || right.length > 0) {
          if (left.length > 0 && right.length > 0) {
            if (compare(left[0], right[0]) <= 0) {
              result.push(left[0]);
              left = left.slice(1);
            } else {
              result.push(right[0]);
              right = right.slice(1);
            }
          } else if (left.length > 0) {
            result.push(left[0]);
            left = left.slice(1);
          } else if (right.length > 0) {
            result.push(right[0]);
            right = right.slice(1);
          }
        }
        return result;
      }

      return merge(
        this.slice(0, middle).mergeSort(compare),
        this.slice(middle, length).mergeSort(compare),
        compare
      );
    };
  }

  // Add merge sort to jQuery if it's present
  if (window.jQuery !== undefined) {
    jQuery.fn.mergeSort = function (compare) {
      return jQuery(Array.prototype.mergeSort.call(this, compare));
    };
    jQuery.mergeSort = function (array, compare) {
      return Array.prototype.mergeSort.call(array, compare);
    };
  }

}());

