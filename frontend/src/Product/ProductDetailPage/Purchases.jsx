import React, { useState, useMemo } from 'react';
import NewPurchaseModal from './NewPurchaseModal';
import FilterModal from './FilterModal';

function Purchases() {
  const [editingId, setEditingId] = useState(null);
  const [editData, setEditData] = useState({});
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isFilterModalOpen, setIsFilterModalOpen] = useState(false);
  const [selectedStatuses, setSelectedStatuses] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');

  const [purchaseData, setPurchaseData] = useState([
    {
      id: 1,
      name: 'Product A',
      quantity: 100,
      unit: 'pcs',
      supplierName: 'John Doe',
      supplierContact: '1234567890',
      originalCost: 2000,
      perUnitCost: 20,
      purchaseDate: '2024-01-15',
      deliveryTime: '2 days',
      deliveryDate: '2024-01-17',
      invoiceNumber: 1001,
      gstinNumber: 123456789,
      gst: 18,
      totalAmount: 2360,
      invoiceUrl: '/sample-invoice.pdf',
      status: 'Delivered'
    },
    {
      id: 2,
      name: 'Product B',
      quantity: 75,
      unit: 'pkts',
      supplierName: 'Jane Smith',
      supplierContact: '9876543210',
      originalCost: 1500,
      perUnitCost: 20,
      purchaseDate: '2024-01-10',
      deliveryTime: '3 days',
      deliveryDate: '2024-01-13',
      invoiceNumber: 1002,
      gstinNumber: 987654321,
      gst: 18,
      totalAmount: 1770,
      invoiceUrl: '/sample-invoice.pdf',
      status: 'Pending'
    },
  ]);
  

  const handleEdit = (purchase) => {
    setEditingId(purchase.id);
    setEditData(purchase);
  };

  const handleSave = () => {
    if (window.confirm('Are you sure you want to save these changes?')) {
      // Here you would typically make an API call to update the data
      setPurchaseData(prevData => 
        prevData.map(item => item.id === editingId ? editData : item)
      );
      setEditingId(null);
      setEditData({});
    }
  };

  const handleCancel = () => {
    setEditingId(null);
    setEditData({});
  };

  const handleDelete = (id) => {
    if (window.confirm('Are you sure you want to delete this purchase record?')) {
      setPurchaseData(prevData => prevData.filter(item => item.id !== id));
    }
  };

  const handleChange = (e, field) => {
    if (field === 'invoice') {
      setEditData({
        ...editData,
        invoiceUrl: e.target.files[0] ? URL.createObjectURL(e.target.files[0]) : '',
        invoiceFile: e.target.files[0]
      });
    } else {
      setEditData({
        ...editData,
        [field]: e.target.value
      });
    }
  };

  const handleAddPurchase = (newPurchase) => {
    setPurchaseData(prevData => [...prevData, {
      ...newPurchase,
      id: prevData.length + 1
    }]);
    setIsModalOpen(false);
  };

  const handleStatusChange = (status, checked) => {
    setSelectedStatuses(prev => 
      checked 
        ? [...prev, status]
        : prev.filter(s => s !== status)
    );
  };

  const filteredPurchaseData = useMemo(() => {
    return purchaseData.filter(purchase => {
      // Apply status filter
      if (selectedStatuses.length > 0 && !selectedStatuses.includes(purchase.status)) {
        return false;
      }
      
      // Apply search filter
      if (searchQuery) {
        const query = searchQuery.toLowerCase();
        return (
          purchase.name.toLowerCase().includes(query) ||
          purchase.supplierName.toLowerCase().includes(query) ||
          purchase.status.toLowerCase().includes(query)
        );
      }
      
      return true;
    });
  }, [purchaseData, selectedStatuses, searchQuery]);

  const getStatusColor = (status) => {
    const colors = {
      Delivered: 'bg-green-100 text-green-800',
      'On Way': 'bg-blue-100 text-blue-800',
      Confirmed: 'bg-purple-100 text-purple-800',
      Pending: 'bg-yellow-100 text-yellow-800',
      Cancelled: 'bg-red-100 text-red-800'
    };
    return colors[status] || 'bg-gray-100 text-gray-800';
  };

  return (
    <div className="space-y-6 p-6">
      <div className="flex flex-col sm:flex-row justify-between items-center gap-4">
        <h2 className="text-2xl font-semibold">Purchase History</h2>
        <div className="flex items-center gap-3 w-full sm:w-auto">
          <div className="relative flex-1 sm:flex-initial">
            <input
              type="text"
              placeholder="Search products..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="pl-9 pr-4 py-2 border rounded-md w-full"
            />
            <span className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                <circle cx="11" cy="11" r="8"/>
                <path d="m21 21-4.3-4.3"/>
              </svg>
            </span>
          </div>
          <button 
            onClick={() => setIsFilterModalOpen(true)}
            disabled={purchaseData.length === 0}
            className={`p-2 border rounded-md hover:bg-gray-50 ${
              selectedStatuses.length > 0 ? 'bg-blue-50 border-blue-200' : ''
            } ${purchaseData.length === 0 ? 'opacity-50 cursor-not-allowed' : ''}`}
          >
            <span className={`text-lg ${
              selectedStatuses.length > 0 ? 'text-blue-600' : 'text-gray-600'}`}>
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                <path d="M22 3H2l8 9.46V19l4 2v-8.54L22 3z"/>
              </svg>
            </span>
          </button>
          <button 
            onClick={() => setIsModalOpen(true)}
            className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors whitespace-nowrap"
          >
            New Purchase
          </button>
        </div>
      </div>

      <div className="bg-white rounded-lg overflow-x-auto shadow-md">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
              <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Quantity/Unit</th>
              <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Supplier Details</th>
              <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Costs</th>
              <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Dates</th>
              <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Invoice Details</th>
              <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
              <th className="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {purchaseData.length === 0 ? (
              <tr>
                <td colSpan="8" className="px-4 py-8 text-center text-gray-500">
                  No purchase history added
                </td>
              </tr>
            ) : filteredPurchaseData.length === 0 ? (
              <tr>
                <td colSpan="8" className="px-4 py-8 text-center text-gray-500">
                  No data record met this condition
                </td>
              </tr>
            ) : (
              filteredPurchaseData.map((purchase) => (
                <tr key={purchase.id}>
                  <td className="px-4 py-4 whitespace-nowrap">
                    {editingId === purchase.id ? (
                      <input
                        type="text"
                        value={editData.name}
                        onChange={(e) => handleChange(e, 'name')}
                        className="border rounded px-2 py-1 w-full"
                      />
                    ) : (
                      <span className="text-sm text-gray-900">{purchase.name}</span>
                    )}
                  </td>

                  <td className="px-4 py-4 whitespace-nowrap">
                    {editingId === purchase.id ? (
                      <div className="space-y-1 flex flex-col w-full">
                        <input
                          type="number"
                          value={editData.quantity}
                          onChange={(e) => handleChange(e, 'quantity')}
                          className="border rounded px-2 py-1 w-full"
                        />
                        <input
                          type="text"
                          value={editData.unit}
                          onChange={(e) => handleChange(e, 'unit')}
                          className="border rounded px-2 py-1 w-full"
                        />
                      </div>
                    ) : (
                      <div className="text-sm text-gray-900">
                        <div>Qty: {purchase.quantity}</div>
                        <div>Unit: {purchase.unit}</div>
                      </div>
                    )}
                  </td>

                  <td className="px-4 py-4 whitespace-nowrap">
                    {editingId === purchase.id ? (
                      <div className="space-y-1 flex flex-col w-full">
                        <input
                          type="text"
                          value={editData.supplierName}
                          onChange={(e) => handleChange(e, 'supplierName')}
                          className="border rounded px-2 py-1 w-full"
                        />
                        <input
                          type="tel"
                          value={editData.supplierContact}
                          onChange={(e) => handleChange(e, 'supplierContact')}
                          className="border rounded px-2 py-1 w-full"
                        />
                      </div>
                    ) : (
                      <div className="text-sm text-gray-900">
                        <div>{purchase.supplierName}</div>
                        <div>{purchase.supplierContact}</div>
                      </div>
                    )}
                  </td>

                  <td className="px-4 py-4 whitespace-nowrap">
                    {editingId === purchase.id ? (
                      <div className="space-y-1 flex flex-col w-full">
                        <input
                          type="number"
                          value={editData.originalCost}
                          onChange={(e) => handleChange(e, 'originalCost')}
                          className="border rounded px-2 py-1 w-full"
                        />
                        <input
                          type="number"
                          value={editData.perUnitCost}
                          onChange={(e) => handleChange(e, 'perUnitCost')}
                          className="border rounded px-2 py-1 w-full"
                        />
                      </div>
                    ) : (
                      <div className="text-sm text-gray-900">
                        <div>Original: ₹{purchase.originalCost}</div>
                        <div>Per Unit: ₹{purchase.perUnitCost}</div>
                      </div>
                    )}
                  </td>

                  <td className="px-4 py-4 whitespace-nowrap">
                    {editingId === purchase.id ? (
                      <div className="space-y-1 flex flex-col w-full">
                        <input
                          type="date"
                          value={editData.purchaseDate}
                          onChange={(e) => handleChange(e, 'purchaseDate')}
                          className="border rounded px-2 py-1 w-full"
                        />
                        <input
                          type="text"
                          value={editData.deliveryTime}
                          onChange={(e) => handleChange(e, 'deliveryTime')}
                          className="border rounded px-2 py-1 w-full"
                        />
                        <input
                          type="date"
                          value={editData.deliveryDate}
                          onChange={(e) => handleChange(e, 'deliveryDate')}
                          className="border rounded px-2 py-1 w-full"
                        />
                      </div>
                    ) : (
                      <div className="text-sm text-gray-900">
                        <div>Purchase: {new Date(purchase.purchaseDate).toLocaleDateString()}</div>
                        <div>Delivery Time: {purchase.deliveryTime}</div>
                        <div>Delivery: {new Date(purchase.deliveryDate).toLocaleDateString()}</div>
                      </div>
                    )}
                  </td>

                  <td className="px-4 py-4 whitespace-nowrap">
                    {editingId === purchase.id ? (
                      <div className="space-y-1 flex flex-col w-full">
                        <input
                          type="number"
                          value={editData.invoiceNumber}
                          onChange={(e) => handleChange(e, 'invoiceNumber')}
                          className="border rounded px-2 py-1 w-full"
                        />
                        <input
                          type="number"
                          value={editData.gstinNumber}
                          onChange={(e) => handleChange(e, 'gstinNumber')}
                          className="border rounded px-2 py-1 w-full"
                        />
                        <input
                          type="number"
                          value={editData.gst}
                          onChange={(e) => handleChange(e, 'gst')}
                          className="border rounded px-2 py-1 w-full"
                        />
                        <input
                          type="number"
                          value={editData.totalAmount}
                          onChange={(e) => handleChange(e, 'totalAmount')}
                          className="border rounded px-2 py-1 w-full"
                        />
                        <div className="flex flex-col gap-2">
                          {editData.invoiceUrl ? (
                            <div className="flex items-center gap-2">
                              <span className="text-sm text-gray-600">
                                Current: {editData.invoiceUrl.split('/').pop()}
                              </span>
                              <button
                                onClick={() => handleChange({ target: { files: [] } }, 'invoice')}
                                className="text-red-600 hover:text-red-800 text-sm"
                              >
                                Remove
                              </button>
                            </div>
                          ) : (
                            <input
                              type="file"
                              onChange={(e) => handleChange(e, 'invoice')}
                              className="text-sm"
                              accept=".pdf,.doc,.docx"
                            />
                          )}
                        </div>
                      </div>
                    ) : (
                      <div className="text-sm text-gray-900">
                        <div>Invoice: #{purchase.invoiceNumber}</div>
                        <div>GSTIN: {purchase.gstinNumber}</div>
                        <div>GST: {purchase.gst}%</div>
                        <div>Total: ₹{purchase.totalAmount}</div>
                        {purchase.invoiceUrl ? (
                          <a
                            href={purchase.invoiceUrl}
                            download
                            className="text-blue-600 hover:text-blue-800 underline text-sm"
                          >
                            Download Invoice
                          </a>
                        ) : (
                          <span className="text-gray-500 text-sm">Invoice unavailable</span>
                        )}
                      </div>
                    )}
                  </td>

                  <td className="px-4 py-4 whitespace-nowrap">
                    {editingId === purchase.id ? (
                      <select
                        value={editData.status}
                        onChange={(e) => handleChange(e, 'status')}
                        className="border rounded px-2 py-1 w-full"
                      >
                        <option value="Delivered">Delivered</option>
                        <option value="On Way">On Way</option>
                        <option value="Confirmed">Confirmed</option>
                        <option value="Pending">Pending</option>
                        <option value="Cancelled">Cancelled</option>
                      </select>
                    ) : (
                      <span className={`px-2 py-1 inline-flex text-xs leading-5 font-semibold rounded-full ${getStatusColor(purchase.status)}`}>
                        {purchase.status}
                      </span>
                    )}
                  </td>

                  <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-500">
                    {editingId === purchase.id ? (
                      <div className="space-x-2">
                        <button
                          onClick={handleSave}
                          className="text-green-600 hover:text-green-900"
                        >
                          ✓
                        </button>
                        <button
                          onClick={handleCancel}
                          className="text-red-600 hover:text-red-900"
                        >
                          ✕
                        </button>
                      </div>
                    ) : (
                      <div className="space-x-2">
                        <button
                          onClick={() => handleEdit(purchase)}
                          className="text-xl text-blue-600 hover:text-blue-900"
                        >
                          ✎
                        </button>
                        <button
                          onClick={() => handleDelete(purchase.id)}
                          className="text-xl text-red-600 hover:text-red-900"
                        >
                          🗑
                        </button>
                      </div>
                    )}
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
      <NewPurchaseModal 
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onSubmit={handleAddPurchase}
      />
      <FilterModal
        isOpen={isFilterModalOpen}
        onClose={() => setIsFilterModalOpen(false)}
        selectedStatuses={selectedStatuses}
        onStatusChange={handleStatusChange}
      />
    </div>
  );
}

export default Purchases;

