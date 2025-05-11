import { useEffect, useState } from 'react'
import axios from 'axios'

export default function Reports() {
  const [records, setRecords] = useState([])
  const [filter, setFilter] = useState('')
  const [vaccines, setVaccines] = useState([])

  useEffect(() => {
    axios.get('http://localhost:8080/api/vaccinations/all',{
       withCredentials: true
    })
      .then(res => {
        setRecords(res.data)
        const uniqueVaccines = [...new Set(res.data.map(r => r.drive.vaccineName))]
        setVaccines(uniqueVaccines)
      })
  }, [])

  const filteredRecords = filter
    ? records.filter(r => r.drive.vaccineName === filter)
    : records

  const downloadCSV = () => {
    const csvHeader = 'Student Name,Student ID,Class,Vaccine,Date\n'
    const csvRows = filteredRecords.map(r =>
      `${r.student.name},${r.student.studentId},${r.student.studentClass},${r.drive.vaccineName},${r.dateGiven}`
    )
    const csvContent = csvHeader + csvRows.join('\n')
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' })
    const link = document.createElement('a')
    link.href = URL.createObjectURL(blob)
    link.download = 'vaccination_report.csv'
    link.click()
  }

  return (
    <div className="p-6 space-y-6">
      <h1 className="text-2xl font-semibold">Vaccination Reports</h1>

      <div className="flex space-x-4 items-center">
        <select value={filter} onChange={e => setFilter(e.target.value)} className="border p-2">
          <option value="">All Vaccines</option>
          {vaccines.map(v => (
            <option key={v} value={v}>{v}</option>
          ))}
        </select>
        <button onClick={downloadCSV} className="bg-blue-500 text-white px-4 py-2 rounded">Download CSV</button>
      </div>

      <table className="table-auto w-full text-left mt-4 bg-white rounded shadow">
        <thead className="bg-gray-200">
          <tr>
            <th className="p-2">Name</th>
            <th className="p-2">Student ID</th>
            <th className="p-2">Class</th>
            <th className="p-2">Vaccine</th>
            <th className="p-2">Date</th>
          </tr>
        </thead>
        <tbody>
          {filteredRecords.map((r, idx) => (
            <tr key={idx} className="border-t">
              <td className="p-2">{r.student.name}</td>
              <td className="p-2">{r.student.studentId}</td>
              <td className="p-2">{r.student.studentClass}</td>
              <td className="p-2">{r.drive.vaccineName}</td>
              <td className="p-2">{r.dateGiven}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
