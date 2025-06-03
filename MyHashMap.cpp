#include <vector>
#include <list>
using namespace std;

class MyHashMap {
private:
    static const int sz = 10007;
    vector<list<pair<int, int>>> buckets;

    int hash(int key) {
        return key % sz;
    }

public:
    MyHashMap() {
        buckets.resize(sz);
    }

    void put(int key, int value) {
        int idx = hash(key);
        for (auto& pair : buckets[idx]) {
            if (pair.first == key) {
                pair.second = value;
                return;
            }
        }
        buckets[idx].emplace_back(key, value);
    }

    int get(int key) {
        int idx = hash(key);
        for (auto& pair : buckets[idx]) {
            if (pair.first == key) return pair.second;
        }
        return -1;
    }

    void remove(int key) {
        int idx = hash(key);
        auto& bucket = buckets[idx];
        for (auto it = bucket.begin(); it != bucket.end(); ++it) {
            if (it->first == key) {
                bucket.erase(it);
                return;
            }
        }
    }
};
