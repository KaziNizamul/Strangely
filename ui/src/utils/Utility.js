/* external imports */
import _isEmpty from 'lodash/isEmpty';
/* constants */
import {
  EMPTY_ARRAY,
  EMPTY_STRING,
  EMPTY_OBJECT,
} from '../resources/shared/global.constant';

class Utility {
  static isEmptyArr = (array = EMPTY_ARRAY) => _isEmpty(array);

  static isEmptyNotArr = (array = EMPTY_ARRAY) => !Utility.isEmptyArr(array);

  static isEmptyStr = (string = EMPTY_STRING) => (typeof string === 'string' && string.trim() === '') || string === null || string === undefined;

  static isNotEmptyStr = (string = EMPTY_STRING) => !Utility.isEmptyStr(string);

  static isObjectDefined = (object = EMPTY_OBJECT) => !_isEmpty(object);

  static isObjectUnDefined = (object = EMPTY_OBJECT) => !Utility.isObjectDefined(object);

  static getSplitString = (string = EMPTY_STRING, ...variableNames) => {
    const splitParts = string.split(' '); // Split the input string by space
    const result = {};

    variableNames.forEach((name, index) => {
      result[name] = splitParts[index] || '';
    });

    return result;
  };
}

export default Utility;
