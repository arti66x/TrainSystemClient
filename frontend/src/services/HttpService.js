import axios from 'axios'
const CRUD_API_BASE_URL = "http://localhost:8080/";
class HttpService {
    getAll(table, _page, _size) {
        return axios.get(CRUD_API_BASE_URL + table,
            { params: { page: _page - 1, size: _size } })
            .then(response => { return response.data })
    }
    getHeaders(table) {
        return axios.get(CRUD_API_BASE_URL + table + "/headers").then(response => { return response.data })
    }
    getForeignKeys(table) {
        return axios.get(CRUD_API_BASE_URL + table + "/foreign-keys").then(response => { return response.data })
    }
    getFkId(table) {
        return axios.get(CRUD_API_BASE_URL + table + "/fk-id").then(response => {
            console.log(response.data)
            return response.data
        })
    }
    createEntity(table, obj) {
        console.log("create")
        console.log(obj)
        return axios.post(CRUD_API_BASE_URL + table, obj).then(response => { return response.data })
    }

    getObjById(table, objId) {
        return axios.get(CRUD_API_BASE_URL + table + "/" + objId).then(response => {
            console.log("get by id")
            console.log(response.data)
            return response.data
        })
    }

    updateEntity(table, objId, obj) {
        console.log("update")
        console.log(obj)
        return axios.put(CRUD_API_BASE_URL + table + "/" + objId, obj).then(response => { return response.data })
    }

    deleteEntity(table, objId) {
        return axios.delete(CRUD_API_BASE_URL + table + "/" + objId).then(response => {
            console.log(response.data)
            return response.data
        })
    }

    getTablesList() {
        return axios.get(CRUD_API_BASE_URL + 'api-info/table-list').then(response => { return response.data })
    }


    getQueriesList() {
        return axios.get(CRUD_API_BASE_URL + 'api-info/query-list').then(response => { return response.data })
    }


    getQueryFormFields(query){
        return axios.get(CRUD_API_BASE_URL + 'query/' + query + "/fields").then(response => {
            return response.data
        })
    }
    getQuerySelectList(query) {
        return axios.get(CRUD_API_BASE_URL + 'query/' + query + "/select-list").then(response => {
            return response.data
        })
    }

    getTableByQuery(query, form, _page, _size) {
        console.log("query")
        form['page']= _page-1
        form['size']= _size

        return axios.put(CRUD_API_BASE_URL + 'query/' + query, form)
            .then(response => { return response.data })
    }
}
export default new HttpService()