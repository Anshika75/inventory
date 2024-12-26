import React, { useState, useEffect } from 'react';
import Placeholder from '/Images/Common/placeholder.png';

export default function Overview({ isEditing }) {
  const [formData, setFormData] = useState({
    productName: 'Paracetmol',
    productId: '456567',
    modelNo: '456567',
    productType: 'Consumable',
    productCategory: 'Fever Medicine',
    itemType: 'Medicine',
    expiryDate: '2023-04-13',
    supplierName: 'John Doe',
    contactNumber: '99999 88888',
    openingStock: '40',
    remainingStock: '34',
    onTheWay: '15',
    thresholdValue: '12',
    department: 'Medicines',
    store: 'Central',
    showService: false
  });

  const [subParts, setSubParts] = useState([
    { name: 'Injection', quantity: 15 },
    { name: 'Tablets', quantity: 19 },
  ]);

  const [suppliers, setSuppliers] = useState([
    { name: 'John Doe', contactNumber: '99999 88888' },
    { name: 'Jane Smith', contactNumber: '88888 77777' },
    { name: 'Bob Johnson', contactNumber: '77777 66666' },
  ]);

  const departments = ['Medicines', 'Surgery', 'Emergency', 'Other'];
  const itemTypes = ['Medicine', 'Equipment', 'Supplies', 'Other'];
  const stores = ['Central', 'Branch 1', 'Branch 2'];
  const productTypes = ['Asset', 'Consumable'];
  const [imagePreview, setImagePreview] = useState(Placeholder);

  const handleImageUpload = (event) => {
    const file = event.target.files[0];
    if (file) {
      const previewURL = URL.createObjectURL(file);
      setImagePreview(previewURL);
    }
  };

  useEffect(() => {
    const selectedSupplier = suppliers.find(s => s.name === formData.supplierName);
    if (selectedSupplier) {
      setFormData(prev => ({ ...prev, contactNumber: selectedSupplier.contactNumber }));
    }
  }, [formData.supplierName]);

  const handleInputChange = (field, value) => {
    setFormData(prev => ({
      ...prev,
      [field]: value
    }));
  };

  const handleSubPartChange = (index, field, value) => {
    const updatedSubParts = [...subParts];
    updatedSubParts[index][field] = value;
    setSubParts(updatedSubParts);
  };

  const addSubPart = () => {
    if (subParts.some(part => part.name === '' || part.quantity === '')) {
      alert('Please fill in all existing sub parts before adding a new one.');
      return;
    }
    setSubParts([...subParts, { name: '', quantity: '' }]);
  };

  const deleteSubPart = (index) => {
    if (window.confirm('Are you sure you want to delete this sub part?')) {
      const updatedSubParts = subParts.filter((_, i) => i !== index);
      setSubParts(updatedSubParts);
    }
  };

  const renderValue = (label, value, field, type = 'text') => {
    if (!isEditing) {
      return <div className='text-value'>{value}</div>;
    }

    switch (type) {
      case 'select':
        return (
          <select
            value={value}
            onChange={(e) => handleInputChange(field, e.target.value)}
            className="w-full p-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            {label === 'Department' && departments.map(opt => (
              <option key={opt} value={opt}>{opt}</option>
            ))}
            {label === 'Item Type' && itemTypes.map(opt => (
              <option key={opt} value={opt}>{opt}</option>
            ))}
            {label === 'Store' && stores.map(opt => (
              <option key={opt} value={opt}>{opt}</option>
            ))}
            {label === 'Product Type' && productTypes.map(opt => (
              <option key={opt} value={opt}>{opt}</option>
            ))}
            {label === 'Supplier name' && suppliers.map(supplier => (
              <option key={supplier.name} value={supplier.name}>{supplier.name}</option>
            ))}
          </select>
        );
      case 'date':
        return (
          <input
            type="date"
            value={value}
            onChange={(e) => handleInputChange(field, e.target.value)}
            className="w-full p-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        );
      case 'number':
        return (
          <input
            type="number"
            value={value}
            onChange={(e) => handleInputChange(field, e.target.value)}
            className="w-full p-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        );
      default:
        return (
          <input
            type="text"
            value={value}
            onChange={(e) => handleInputChange(field, e.target.value)}
            className="w-full p-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        );
    }
  };

  return (
    <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
      <div className="lg:col-span-2 space-y-8">
        {/* Primary Details */}
        <section>
          <h2 className="text-lg font-semibold text-seconday mb-4">Primary Details</h2>
          <div className="grid gap-4">
            <div className="grid grid-cols-2 gap-4">
              <div className="text-key font-medium">Product name</div>
              {renderValue('Product name', formData.productName, 'productName')}
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div className="text-key font-medium">Product ID</div>
              {renderValue('Product ID', formData.productId, 'productId')}
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div className="text-key font-medium">Model No</div>
              {renderValue('Model No', formData.modelNo, 'modelNo')}
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div className="text-key font-medium">Product Type</div>
              {renderValue('Product Type', formData.productType, 'productType', 'select')}
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div className="text-key font-medium">Product category</div>
              {renderValue('Product category', formData.productCategory, 'productCategory')}
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div className="text-key font-medium">Item Type</div>
              {renderValue('Item Type', formData.itemType, 'itemType', 'select')}
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div className="text-key font-medium">Expiry Date</div>
              {renderValue('Expiry Date', formData.expiryDate, 'expiryDate', 'date')}
            </div>
          </div>
        </section>

        {/* Supplier Details */}
        <section>
          <h2 className="text-lg font-semibold text-seconday mb-4">Supplier Details</h2>
          <div className="grid gap-4">
            <div className="grid grid-cols-2 gap-4">
              <div className="text-key font-medium">Supplier name</div>
              {renderValue('Supplier name', formData.supplierName, 'supplierName', 'select')}
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div className="text-key font-medium">Contact Number</div>
              <div className='text-value'>{formData.contactNumber}</div>
            </div>
          </div>
        </section>

        {/* Sub Parts */}
        <section>
          <h2 className="text-lg font-semibold text-seconday mb-4">Sub Parts</h2>
          <div className="rounded-lg overflow-hidden border border-gray-200">
            <div className="grid grid-cols-3 gap-4 p-4 font-medium bg-[#F0F1F3]">
              <div className='text-tertiary font-semibold'>Parts Name</div>
              <div className='text-tertiary font-semibold'>Parts in hand</div>
              {isEditing && <div className='text-tertiary font-semibold'>Actions</div>}
            </div>
            <div className="divide-y">
              {subParts.map((part, index) => (
                <div key={index} className="grid grid-cols-3 gap-4 p-4">
                  <div className='text-value'>
                    {isEditing ? (
                      <input
                        type="text"
                        value={part.name}
                        onChange={(e) => handleSubPartChange(index, 'name', e.target.value)}
                        className="w-full p-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                      />
                    ) : (
                      part.name
                    )}
                  </div>
                  <div className="text-value">
                    {isEditing ? (
                      <input
                        type="number"
                        value={part.quantity}
                        onChange={(e) => handleSubPartChange(index, 'quantity', e.target.value)}
                        className="w-full p-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                      />
                    ) : (
                      part.quantity
                    )}
                  </div>
                  {isEditing && (
                    <div>
                      <button
                        onClick={() => deleteSubPart(index)}
                        className="text-red-600 hover:text-red-800"
                      >
                        Delete
                      </button>
                    </div>
                  )}
                </div>
              ))}
            </div>
          </div>
          {isEditing && (
            <button
              onClick={addSubPart}
              className="mt-4 px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
            >
              Add Sub Part
            </button>
          )}
        </section>
      </div>

      <div className="lg:col-span-1">
        {/* Product Image */}
        <div className="border-2 border-dashed rounded-lg p-4 mb-6">
          <div className="bg-gray-100 aspect-square rounded-lg">
          {isEditing ? (
              <label className="w-full h-full flex items-center justify-center cursor-pointer relative">
                <input
                  type="file"
                  className="hidden"
                  accept="image/*"
                  onChange={handleImageUpload}
                />
                  <span className="absolute text-white z-20 px-12 py-4 bg-gray-700 bg-opacity-70">Click to upload image</span>
                <img
                  src={imagePreview}
                  alt="Uploaded Preview"
                  className="object-cover h-full w-full"
                />
              </label>
            ) : (
              <img
                src={imagePreview}
                alt="Product"
                className="object-cover h-full w-full"
              />
            )}
          </div>
        </div>

        {/* Stock Information */}
        <div className="space-y-4">
          <div className="flex justify-between items-center gap-2">
            <span className="text-key font-medium w-full">Opening Stock</span>
            {renderValue('Opening Stock', formData.openingStock, 'openingStock', 'number')}
          </div>
          <div className="flex justify-between items-center gap-2">
            <span className="text-key font-medium w-full">Remaining Stock</span>
            {renderValue('Remaining Stock', formData.remainingStock, 'remainingStock', 'number')}
          </div>
          <div className="flex justify-between items-center gap-2">
            <span className="text-key font-medium w-full">On the way</span>
            {renderValue('On the way', formData.onTheWay, 'onTheWay', 'number')}
          </div>
          <div className="flex justify-between items-center gap-2">
            <span className="text-key font-medium w-full">Threshold value</span>
            {renderValue('Threshold value', formData.thresholdValue, 'thresholdValue', 'number')}
          </div>
          <div className="flex justify-between items-center gap-2">
            <span className="text-key font-medium w-full">Department</span>
            {renderValue('Department', formData.department, 'department', 'select')}
          </div>
          <div className="flex justify-between items-center gap-2">
            <span className="text-key font-medium w-full">Store</span>
            {renderValue('Store', formData.store, 'store', 'select')}
          </div>
        </div>
      </div>
    </div>
  );
}

