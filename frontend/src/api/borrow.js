import request from './request'

export function borrowBook(bookId) {
  return request.post('/borrows', { bookId })
}

export function returnBook(id) {
  return request.put(`/borrows/${id}/return`)
}

export function renewBook(id) {
  return request.put(`/borrows/${id}/renew`)
}

export function getMyBorrows() {
  return request.get('/borrows/my')
}

export function getAllBorrows(params) {
  return request.get('/borrows', { params })
}

export function getStatistics() {
  return request.get('/borrows/statistics')
}
