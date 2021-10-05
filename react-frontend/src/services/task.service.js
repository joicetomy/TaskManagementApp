import http from "../http-common";

class TaskDataService {
  getAll() {
    return http.get("/tasks");
  }

  create(data) {
    return http.post("/task/add", data);
  }

  update(id,data) {
    return http.put(`/task/complete/${id}`,data);
  }

  deleteTask(id) {
    return http.delete(`/task/${id}`);
  }

  
}

export default new TaskDataService();