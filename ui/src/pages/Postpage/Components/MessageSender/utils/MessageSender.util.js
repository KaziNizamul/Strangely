/* constant */
import { EMPTY_STRING } from '../../../../../resources/shared/global.constant';
import { COMMON_WORDS } from '../constant/MessageSender.constant';

class MessageSenderUtils {
  static getSummaryText = ({
    description = EMPTY_STRING,
    maxWords = 5,
  }) => {
    const words = description.split(' ');
    const wordCounts = words.reduce((counts, word) => {
      if (!COMMON_WORDS.includes(word)) {
        if (counts[word]) {
          // eslint-disable-next-line no-param-reassign
          counts[word] += 1;
        } else {
          // eslint-disable-next-line no-param-reassign
          counts[word] = 1;
        }
      }
      return counts;
    }, {});

    const sortedWords = Object.entries(wordCounts).sort((a, b) => b[1] - a[1]);
    const summaryWords = sortedWords.slice(0, maxWords).map(entry => entry[0]);
    return summaryWords.join(' ');
  };

  static toBase64 = file => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });
}

export default MessageSenderUtils;
