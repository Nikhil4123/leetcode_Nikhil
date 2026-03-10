class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False

        s_list = list(s)
        t_list = list(t)

        for ch in s_list:
            if ch in t_list:
                t_list.remove(ch)
            else:
                return False

        return True