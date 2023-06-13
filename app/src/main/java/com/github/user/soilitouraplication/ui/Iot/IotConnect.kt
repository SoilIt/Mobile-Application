//package com.github.user.soilitouraplication.ui.Iot;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothSocket;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import com.github.user.soilitouraplication.databinding.ActivityIotConnectBinding;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.UUID;
//
//public class IotConnect extends AppCompatActivity {
//    private ActivityIotConnectBinding binding;
//
//    private ListView listViewDevices;
//    private BluetoothAdapter bluetoothAdapter;
//    private ArrayList<BluetoothDevice> discoveredDevices;
//    private ArrayAdapter<String> deviceListAdapter;
//
//    private BluetoothSocket bluetoothSocket;
//    private boolean isConnected = false;
//
//    private final UUID uuid =
//    UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // UUID for Bluetooth SPP connection
//
//    private InputStream inputStream;
//
//    private static final int REQUEST_ENABLE_BLUETOOTH = 1;
//    private static final int REQUEST_BLUETOOTH_PERMISSION = 2;
//    private static final int REQUEST_LOCATION_PERMISSION = 3;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityIotConnectBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        listViewDevices = findViewById(R.id.listDevices);
//        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        discoveredDevices = new ArrayList<>();
//        deviceListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
//
//        listViewDevices.setAdapter(deviceListAdapter);
//
//        listViewDevices.setOnItemClickListener((parent, view, position, id) -> {
//        BluetoothDevice selectedDevice = discoveredDevices.get(position);
//        connectToDevice(selectedDevice);
//    });
//
//        binding.btnConnect.setOnClickListener(v -> {
//        if (isBluetoothEnabled()) {
//            checkBluetoothPermission();
//        } else {
//            requestEnableBluetooth();
//        }
//    });
//
//        binding.btnDisconnect.setOnClickListener(v -> disconnectFromESP32());
//    }
//
//    private boolean isBluetoothEnabled() {
//        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
//    }
//
//    private void requestEnableBluetooth() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN)
//            == PackageManager.PERMISSION_GRANTED) {
//            enableBluetooth();
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_ADMIN},
//                REQUEST_ENABLE_BLUETOOTH);
//        }
//    }
//
//    private void enableBluetooth() {
//        if (bluetoothAdapter != null) {
//            if (!bluetoothAdapter.isEnabled()) {
//                Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BLUETOOTH);
//            } else {
//                checkBluetoothPermission();
//            }
//        } else {
//            Toast.makeText(this, "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
//            if (resultCode == RESULT_OK) {
//                checkBluetoothPermission();
//            } else {
//                Toast.makeText(this, "Bluetooth is required to connect to ESP32", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void checkBluetoothPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH)
//            == PackageManager.PERMISSION_GRANTED) {
//            scanForDevices();
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH},
//                REQUEST_BLUETOOTH_PERMISSION);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_BLUETOOTH_PERMISSION) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                scanForDevices();
//            } else {
//                Toast.makeText(this, "Bluetooth permission denied", Toast.LENGTH_SHORT).show();
//            }
//        } else if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                enableBluetooth();
//            } else {
//                Toast.makeText(this, "Bluetooth permission denied", Toast.LENGTH_SHORT).show();
//            }
//        } else if (requestCode == REQUEST_LOCATION_PERMISSION) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                scanForDevices();
//            } else {
//                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void scanForDevices() {
//        BroadcastReceiver discoveryCallback = new BroadcastReceiver() {
//            @SuppressLint("MissingPermission")
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String action = intent.getAction();
//                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                    if (device != null && !discoveredDevices.contains(device)) {
//                        discoveredDevices.add(device);
//                        deviceListAdapter.add(device.getName() != null ? device.getName() : "Unknown Device");
//                        deviceListAdapter.notifyDataSetChanged();
//                    }
//                }
//            }
//        };
//        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//        registerReceiver(discoveryCallback, filter);
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//            == PackageManager.PERMISSION_GRANTED) {
//            bluetoothAdapter.startDiscovery();
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                REQUEST_LOCATION_PERMISSION);
//        }
//    }
//
//    private void connectToDevice(BluetoothDevice device) {
//        try {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//                bluetoothAdapter.cancelDiscovery();
//                bluetoothSocket = device.createRfcommSocketToServiceRecord(uuid);
//                bluetoothSocket.connect();
//                isConnected = true;
//                inputStream = bluetoothSocket.getInputStream();
//                Toast.makeText(this, "Connected to ESP32", Toast.LENGTH_SHORT).show();
//                updateButtonState();
//
//                // Get temperature data once
//                readTemperatureOnce();
//            } else {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    REQUEST_LOCATION_PERMISSION);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Connection failed", Toast.LENGTH_SHORT).show();
//        } catch (SecurityException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Bluetooth permission denied", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void disconnectFromESP32() {
//        try {
//            if (inputStream != null) {
//                inputStream.close();
//            }
//            if (bluetoothSocket != null) {
//                bluetoothSocket.close();
//            }
//            isConnected = false;
//            Toast.makeText(this, "Disconnected from ESP32", Toast.LENGTH_SHORT).show();
//            updateButtonState();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Disconnection failed", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void updateButtonState() {
//        binding.btnConnect.setEnabled(!isConnected);
//        binding.btnDisconnect.setEnabled(isConnected);
//    }
//
//    private void readTemperatureOnce() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    byte[] buffer = new byte[1024];
//                    int bytes = inputStream.read(buffer);
//
//                    if (bytes > 0) {
//                        String temperatureData = new String(buffer, 0, bytes, "UTF-8");
//                        // Manipulate received temperature data according to the format sent from ESP32
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                binding.tvTemperature.setText(temperatureData);
//                            }
//                        });
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//}
