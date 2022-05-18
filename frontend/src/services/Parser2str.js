class Parser2str{ 
    parse2(data) {
        if (data === null)
            return '';
        else if (typeof data === 'string') {
            return data;
        }
        else if (typeof data === 'object') {
            let keys = Object.keys(data)
            if (keys.length === 1)
                return data[keys[0]]
            else if (keys.length > 1) {
                let strList = []
                for (let k of keys) {
                    if (k === 'id') continue
                    strList.push(this.parse2(data[k]))
                }
                return strList.join(' ')
            } else return null
        }
        else {
            return data.toString();
        }
    }

    parse(data) {
        if (data === null)
            return '';
        else if (typeof data === 'string') {
            return data;
        }
        else if (typeof data === 'object') {
            let keys = Object.keys(data)
            if (keys.length === 1)
                return data[keys[0]]
            else if (keys.length > 1) {
                let strList = []
                for (let k of keys) {
                    if (k === 'id') //continue
                     strList.push('('+this.parse2(data[k])+')')
                    else
                    strList.push(this.parse2(data[k]))
                }
                return strList.join(' ')
            } else return null
        }
        else {
            return data.toString();
        }
    }
}
export default new Parser2str()